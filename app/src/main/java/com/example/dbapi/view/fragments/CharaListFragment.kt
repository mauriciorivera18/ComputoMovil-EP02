package com.example.dbapi.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dbapi.R
import com.example.dbapi.data.remote.CharaApi
import com.example.dbapi.databinding.FragmentGamesListBinding
import com.example.dbapi.utils.Constants
import com.example.dbapi.view.CharaAdapter
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharaListFragment : Fragment() {

    private var _binding : FragmentGamesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGamesListBinding.inflate(inflater, container, false)
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
                binding.pbLoading.visibility = View.INVISIBLE
                //val games = gamesApi.getGames()
                binding.tvError3.visibility = View.INVISIBLE
                val games = charaApi.getChara()
                Log.d(Constants.LOGTAG, "Respuesta: $games")
                binding.rvGames.layoutManager = LinearLayoutManager(requireContext())
                //binding.rvGames.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvGames.adapter = CharaAdapter(games.item){ game ->
                    //Manejo del click
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CharaDetailFragment.newInstance(
                            game.id.toString()
                        ))
                        .addToBackStack(null)
                        .commit()
                }
            }
            catch (e: Exception){
                binding.tvError3.visibility = View.VISIBLE
            }
            finally {
                binding.pbLoading.visibility = View.INVISIBLE
                binding.pdLoading2.visibility = View.INVISIBLE
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}