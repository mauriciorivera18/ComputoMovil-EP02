package com.example.dbapi.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dbapi.R
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapi.data.remote.CharaApi
import com.example.dbapi.databinding.FragmentGameDetailBinding
import com.example.dbapi.utils.Constants
import com.example.dbapi.view.CharaAdapter
import com.example.dbapi.view.TransformationAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.time.Duration.Companion.milliseconds


private const val ARG_ID = "id"


class CharaDetailFragment : Fragment() {

    private var _binding: FragmentGameDetailBinding? = null
    private val binding get() = _binding!!

    private var id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString(ARG_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        //Instanciamos nuestra interfaz a nuestra Api

        val charaApi = retrofit.create(CharaApi::class.java)

        lifecycleScope.launch {


            try{
                binding.tvAffiliationPh.visibility = View.INVISIBLE
                binding.tvKiPh.visibility = View.INVISIBLE
                binding.tvRacePh.visibility = View.INVISIBLE
                binding.tvGenderPh.visibility = View.INVISIBLE
                binding.tvTransformationsPh.visibility = View.INVISIBLE
                binding.tvMaxKiPh.visibility = View.INVISIBLE
                binding.tvError.visibility = View.INVISIBLE
                binding.pbLoading.visibility = View.INVISIBLE

                val chara = charaApi.getCharaDetail(id)
                if(chara.name.isEmpty()){
                    throw Exception()
                }
                binding.tvName.text = chara.name
                binding.tvDesc.text = chara.description
                binding.tvKi.text = chara.ki
                binding.tvKi2.text = chara.maxKi
                binding.tvRace.text = chara.gender
                binding.tvGender.text = chara.race
                binding.tvAffiliation.text = chara.aff

                if(chara.transformation.isNotEmpty())
                    binding.tvNoTransform.text = Constants.NOTHING
                    binding.rvTransformation.layoutManager = GridLayoutManager(requireContext(), 2)
                    binding.rvTransformation.adapter =
                        TransformationAdapter(chara.transformation){ transf ->
                        requireActivity().supportFragmentManager.beginTransaction()
                            .addToBackStack(null)
                            .commit()
                    }
                    Picasso.get()
                        .load(chara.image)
                        .into(binding.ivImage)

                binding.tvAffiliationPh.visibility = View.VISIBLE
                binding.tvKiPh.visibility = View.VISIBLE
                binding.tvRacePh.visibility = View.VISIBLE
                binding.tvGenderPh.visibility = View.VISIBLE
                binding.tvTransformationsPh.visibility = View.VISIBLE
                binding.tvMaxKiPh.visibility = View.VISIBLE
            }catch (e: Exception){
                binding.tvError.visibility = View.VISIBLE
                binding.tvNoTransform.text = Constants.NOTHING
            }finally {
                binding.pbLoading.visibility = View.INVISIBLE
                binding.pdLoading2.visibility = View.INVISIBLE
            }


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(id: String) =
            CharaDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id)
                }
            }
    }
}