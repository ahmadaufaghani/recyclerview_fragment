package com.example.motionassignment2.bnv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.motionassignment2.R
import com.example.motionassignment2.databinding.FragmentCourseDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CourseDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseDetailsFragment : Fragment() {
    private val args: CourseDetailsFragmentArgs by navArgs()
    private var _binding : FragmentCourseDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCourseDetailsBinding.inflate(layoutInflater)
        binding.apply {
            ivItemDetails.setImageResource(args.courseImage)
            titleDetails.setText(args.courseTitle)
            subtitleDetails.setText(args.courseTitle)
            ratingLabel.setText(args.courseRating)
            questionTotal.setText(args.questionTotal)
            timeTotal.setText(args.duration)
            if(args.status == "in_progress") {
                btnSubdetails.text="Continue Test"
            } else {
                btnSubdetails.text = "Revisit"
            }
            btnBack.setOnClickListener {
                backToHome()
            }

        }
        return binding.root
    }

    private fun backToHome() {
        val action = CourseDetailsFragmentDirections.actionCourseDetailsFragmentToActionHome2()
        findNavController().navigate(action)
    }


}