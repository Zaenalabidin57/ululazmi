package com.example.ululazmi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.btnNuh).setOnClickListener {
            val nuhFragment = nuhFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, nuhFragment)
                .commit()
        }

        view.findViewById<Button>(R.id.btnIbrahim).setOnClickListener {
            val ibrahimFragment = ibrahimFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ibrahimFragment)
                .commit()
        }

        view.findViewById<Button>(R.id.btnMusa).setOnClickListener {
            val musaFragment = musaFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, musaFragment)
                .commit()
        }

        view.findViewById<Button>(R.id.btnIsa).setOnClickListener {
            val isaFragment = isaFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, isaFragment)
                .commit()
        }

        view.findViewById<Button>(R.id.btnMuhammad).setOnClickListener {
            val muhammadFragment = muhammadFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, muhammadFragment)
                .commit()
        }
        view.findViewById<Button>(R.id.btnAbout).setOnClickListener {
            val aboutFragment = AboutFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, aboutFragment)
                .commit()
        }
        view.findViewById<Button>(R.id.btnQuiz).setOnClickListener {
            Log.d("HomeFragment", "Quiz button clicked")
            val quizFragment = QuizFragment.newInstance(param1 ?: "", param2 ?: "")
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, quizFragment)

                .commit()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}