package com.example.nc_clone

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import java.util.Timer
import java.util.TimerTask

class NCPlusFragmentActivity : Fragment() {
    private lateinit var viewPager: ViewPager2
    private lateinit var timer: Timer
    private var currentPage = 0
    private var autoScrollDelay: Long = 3000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nc_plus_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkCalls: NetworkCalls = NetworkCalls(requireContext())

        networkCalls.getNCPlusContent(object: NetworkResponseInterface {
            override fun success(responseOne: MutableList<Teacher>?, responseTwo: TeacherDetail?, responseThree: NCPlusContent?) {
                if(responseThree != null) {
                    val imageLinkList = arrayListOf<String>()

                    for(i in 0 until responseThree.dailyNewsLatest.length()) {
                        imageLinkList.add(responseThree.dailyNewsLatest.getJSONObject(i).getString("image"))
                    }

                    viewPager = view.findViewById(R.id.nc_plus_carousel_pager)
                    viewPager.adapter = NCPlusLatestNewsCarouselAdapter(imageLinkList)

                    val marginPx = resources.getDimensionPixelOffset(R.dimen.viewpager_margin)
                    viewPager.addItemDecoration(MarginItemDecoration(marginPx))

                    val startPosition = Int.MAX_VALUE / 2 - (Int.MAX_VALUE / 2 % imageLinkList.size)
                    viewPager.setCurrentItem(startPosition, false)

                    viewPager.setPageTransformer { page, position ->
                        page.apply {
                            val scaleFactor = 0.85f + (1 - Math.abs(position)) * 0.15f
                            scaleX = scaleFactor
                            scaleY = scaleFactor
                            alpha = 0.5f + (1 - Math.abs(position)) * 0.5f // Optional transparency
                        }
                    }

                    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            currentPage = position
                            resetAutoScroll()
                        }
                    })

                    startAutoScroll()
                }
            }

            override fun error(error: String) {
                Log.d(error, "test-here-network")
            }
        })
    }

    private fun startAutoScroll() {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            viewPager.setCurrentItem(currentPage + 1, true) // Enable smooth scrolling
        }

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(update)
            }
        }, autoScrollDelay, autoScrollDelay)
    }

    private fun resetAutoScroll() {
        timer.cancel() // Cancel the current timer
        startAutoScroll() // Start a new timer
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel() // Cancel the timer when activity is destroyed to avoid memory leaks
    }

}