package com.example.motionassignment2.bnv

import android.app.Activity.RESULT_OK
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.motionassignment2.R
import com.example.motionassignment2.databinding.FragmentAddBinding
import com.example.motionassignment2.model.Courses
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.ByteArrayOutputStream
import java.util.Base64

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    private var courseImages :String = ""
    private lateinit var databaseReference:DatabaseReference
    private lateinit var storageReference: StorageReference
    private var _binding : FragmentAddBinding? = null
    private var uri: Uri? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        databaseReference = FirebaseDatabase.getInstance().getReference("course")
        binding.apply{
            btnAddImage.setOnClickListener {
                ActivityResultLauncher.launch("image/*")
            }
            btnSubmit.setOnClickListener {
                val courseTitle = binding.tiCourseTitleInput.text.toString()
                val courseAnsweredQuestion = binding.tiCourseAnsweredQuestionInput.text.toString()
                val totalQuestion = binding.tiCourseTotalQuestionInput.text.toString()
                val duration = binding.tiCourseDurationInput.text.toString()
                val status = binding.tiCourseStatusInput.text.toString()
                val rating = binding.tiCourseRatingInput.text.toString()

                var item : Courses
                storageReference = FirebaseStorage.getInstance().getReference("images")
                val courseId = databaseReference.push().key

                uri?.let {
                    storageReference.child(courseId!!).putFile(it).addOnSuccessListener {
                        task -> task.metadata!!.reference!!.downloadUrl.addOnSuccessListener {
                           url ->
                            val courseImage = url.toString()
                            item = Courses(courseImage, courseTitle, courseAnsweredQuestion, totalQuestion,duration, status, rating)
                        databaseReference.child(courseId).setValue(item).addOnCompleteListener {
                            Toast.makeText(activity, "Course successfully added!",Toast.LENGTH_SHORT).show()
                            val action = AddFragmentDirections.actionAddFragmentToActionHome()
                            findNavController().navigate(action)
                        }.addOnFailureListener {
                            Toast.makeText(activity, "Course failed to add!",Toast.LENGTH_SHORT).show()
                        }
                    }
                    }
                }


            }
            btnBack.setOnClickListener {
                val action = AddFragmentDirections.actionAddFragmentToActionHome()
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

    private val ActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){
        binding.ivAddImage.setImageURI(it)
       if(it != null) {
           uri = it
       }

    }

}