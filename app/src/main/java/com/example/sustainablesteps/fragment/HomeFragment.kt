package com.example.sustainablesteps.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import com.denzcoskun.imageslider.constants.ScaleTypes
//import com.denzcoskun.imageslider.models.SlideModel
import com.example.sustainablesteps.R
import com.example.sustainablesteps.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val imageList = ArrayList<SlideModel>()
//        imageList.add(SlideModel(R.drawable.volunteering_banner1, ScaleTypes.FIT))
//        imageList.add(SlideModel(R.drawable.volunteering_banner2, ScaleTypes.FIT))
//        imageList.add(SlideModel(R.drawable.volunteering_banner3, ScaleTypes.FIT))
//        imageList.add(SlideModel(R.drawable.volunteering_banner4, ScaleTypes.FIT))
//
//        val imageSlider = binding.imageSlider
//        imageSlider.setImageList(imageList)
//        imageSlider.setImageList(imageList, ScaleTypes.FIT)
//
//        val imageList2 = ArrayList<SlideModel>()
//        imageList2.add(SlideModel(R.drawable.extra_banner1, ScaleTypes.FIT))
//        imageList2.add(SlideModel(R.drawable.extra_banner2, ScaleTypes.FIT))
//        imageList2.add(SlideModel(R.drawable.extra_banner3, ScaleTypes.FIT))
//
//        val imageSlider2 = binding.imageSlider2
//        imageSlider2.setImageList(imageList2)
//        imageSlider2.setImageList(imageList2, ScaleTypes.FIT)


    }

    companion object {

    }
}