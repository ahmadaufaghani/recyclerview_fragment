package com.example.motionassignment2.bnv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motionassignment2.R
import com.example.motionassignment2.adapter.CoursesAdapter
import com.example.motionassignment2.model.Courses

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var adapter : CoursesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var coursesList : List<Courses>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rv_courseitem)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = CoursesAdapter(coursesList)
        recyclerView.adapter = adapter
    }

    private fun initialize() {
        coursesList = listOf<Courses>(
            Courses(
                1,
                R.drawable.item_img,
                "React JS",
                "8",
                "10"
                ,"35 minutes",
                "in_progress",
                "4,5"
            ),Courses(
                2,
                R.drawable.item_img,
                "React JS",
                "8",
                "10"
                ,"35 minutes",
                "in_progress",
                "4,7"
            ),Courses(
                3,
                R.drawable.item_img,
                "React JS",
                "8",
                "10"
                ,"35 minutes",
                "completed",
                "4,8"
            ),Courses(
                4,
                R.drawable.item_img,
                "React JS",
                "8",
                "10"
                ,"35 minutes",
                "completed",
                "4,9"
            ),
        )
    }

}