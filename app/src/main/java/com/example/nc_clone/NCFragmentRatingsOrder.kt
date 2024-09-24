package com.example.nc_clone

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NCFragmentRatingsOrder : Fragment() {
    lateinit var teacherList: MutableList<Teacher>
    private lateinit var listener: OnTeacherFragmentClicked

    interface OnTeacherFragmentClicked {
        fun onContainerClicked(teacherDetail: TeacherDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.nc_ratings_order, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnTeacherFragmentClicked) {
            listener = context
        } else {
            Log.d("test-here-error", "test-here-error")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teacherListView: RecyclerView = view.findViewById(R.id.nc_teacher_rating_order_list)
        teacherListView.layoutManager = LinearLayoutManager(requireContext())
        val networkCalls = NetworkCalls(requireContext())

        networkCalls.getTeacherList(object: NetworkResponseInterface {
            override fun success(responseOne: MutableList<Teacher>?, responseTwo: TeacherDetail?, responseThree: NCPlusContent?) {
                if (responseOne != null) {
                    teacherList = responseOne

                    val adapter = RecyclerTeacherListAdapter(teacherList, requireContext(), object: RecyclerTeacherListAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                            networkCalls.getTeacherDetails(object: NetworkResponseInterface {
                                override fun success(responseOne: MutableList<Teacher>?, responseTwo: TeacherDetail?, responseThree: NCPlusContent?) {
                                    if(responseTwo != null) {
                                        listener.onContainerClicked(responseTwo)
                                    }
                                }

                                override fun error(error: String) {
                                    Log.d(error, "test-here-network")
                                }
                            }, teacherList[position].id)
                        }
                    })

                    teacherListView.adapter = adapter
                }
            }

            override fun error(error: String) {
                Log.d(error, "test-here-network")
            }
        })
    }
}