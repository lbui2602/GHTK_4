package com.example.ghtk_4.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ghtk_4.R
import com.example.ghtk_4.adapter.HistoryAdapter
import com.example.ghtk_4.databinding.FragmentOneBinding
import com.example.ghtk_4.databinding.FragmentThreeBinding
import com.example.ghtk_4.viewmodel.OneViewModel
import com.example.ghtk_4.viewmodel.ThreeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ThreeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ThreeFragment : Fragment() {
    private var _binding: FragmentThreeBinding?= null
    private val binding get() = _binding!!
    private val threeViewModel: ThreeViewModel by viewModels()
    private lateinit var historyAdapter: HistoryAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
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
        _binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ThreeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ThreeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linearLayout.visibility=View.GONE
        binding.progressBar.visibility=View.VISIBLE
        coroutineScope.launch {
            delay(2000) // 2000 milliseconds = 2 seconds
            // Hiển thị nội dung sau khi delay
            binding.progressBar.visibility=View.GONE
            binding.linearLayout.visibility = View.VISIBLE
        }
        historyAdapter = HistoryAdapter()
        binding.rcvHistory.apply {
            layoutManager=LinearLayoutManager(context)
            adapter=historyAdapter
        }
        threeViewModel.loadUserFromAssets(requireContext())
        threeViewModel.user.observe(viewLifecycleOwner, Observer { user->
            user?.let {
                binding.tvFullName.text=user.fullName
                binding.tvPosition.text=user.position
                historyAdapter.differ.submitList(user.history)
            }
        })
    }
}