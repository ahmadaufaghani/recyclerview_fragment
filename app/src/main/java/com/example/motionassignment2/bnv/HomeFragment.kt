package com.example.motionassignment2.bnv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.motionassignment2.R
import com.example.motionassignment2.adapter.CoursesAdapter
import com.example.motionassignment2.databinding.FragmentHomeBinding
import com.example.motionassignment2.model.Courses
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

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
    private lateinit var  firebaseRef : DatabaseReference
    private lateinit var adapter : CoursesAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var coursesList : ArrayList<Courses>
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.apply {
                fab.setOnClickListener {
                    val action = HomeFragmentDirections.actionActionHomeToAddFragment()
                    findNavController().navigate(action)
                }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.rv_courseitem)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.setHasFixedSize(true)
        coursesList = arrayListOf()
        firebaseRef = FirebaseDatabase.getInstance().getReference("course")
        getDataFromDB()

    }

    private fun getDataFromDB() {

        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                coursesList.clear()
                if(snapshot.exists()) {
                    for(coursesSnap in snapshot.children) {
                        val coursesData = coursesSnap.getValue(Courses::class.java)
                        coursesList.add(coursesData!!)
                    }
                }
                val coursesAdapter = CoursesAdapter(coursesList)
                recyclerView.adapter = coursesAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

//    private fun initialize() {
//        coursesList = listOf<Courses>(
//            Courses(
//                "R.drawable.item_img",
//                "React JS",
//                "8",
//                "10"
//                ,"35 minutes",
//                "in_progress",
//                "4,5"
//            ),Courses(
//                "R.drawable.item_img",
//                "React JS",
//                "8",
//                "10"
//                ,"35 minutes",
//                "in_progress",
//                "4,7"
//            ),Courses(
//                "R.drawable.item_img",
//                "React JS",
//                "8",
//                "10"
//                ,"35 minutes",
//                "completed",
//                "4,8"
//            ),Courses(
//                "R.drawable.item_img",
//                "React JS",
//                "8",
//                "10"
//                ,"35 minutes",
//                "completed",
//                "4,9"
//            ),
//        )
//    }

}