package com.example.ghtk_4.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.ghtk_4.R
import com.example.ghtk_4.databinding.FragmentOneBinding
import com.example.ghtk_4.databinding.FragmentTwoBinding
import com.example.ghtk_4.viewmodel.OneViewModel
import com.example.ghtk_4.viewmodel.TwoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
    private lateinit var binding: FragmentTwoBinding
    private val twoViewModel: TwoViewModel by viewModels()

    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSubmit2.setOnClickListener {
            try {
                val x: Float = binding.edtXX.text.toString().toFloat()
                val y: Float = binding.edtYX.text.toString().toFloat()
                val x1: Float = binding.edtXA.text.toString().toFloat()
                val y1: Float = binding.edtYA.text.toString().toFloat()
                val x2: Float = binding.edtXB.text.toString().toFloat()
                val y2: Float = binding.edtYB.text.toString().toFloat()
                val x3: Float = binding.edtXC.text.toString().toFloat()
                val y3: Float = binding.edtYC.text.toString().toFloat()
                twoViewModel.check(x, y, x1, y1, x2, y2, x3, y3)
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    requireContext(),
                    "Vui lòng nhập vào ô trống đúng định dạng số thập phân",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
        twoViewModel.result.observe(viewLifecycleOwner, Observer { result ->
            binding.tvResult2.text = result
        })
    }
}