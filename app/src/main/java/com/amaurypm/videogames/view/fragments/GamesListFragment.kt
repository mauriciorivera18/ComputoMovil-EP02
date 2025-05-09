package com.amaurypm.videogames.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaurypm.videogames.R
import com.amaurypm.videogames.data.remote.GamesApi
import com.amaurypm.videogames.databinding.FragmentGamesListBinding
import com.amaurypm.videogames.databinding.GameElementBinding
import com.amaurypm.videogames.utils.Constants
import com.amaurypm.videogames.view.GameAdapter
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GamesListFragment : Fragment() {

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


        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Instanciamos nuestra interfaz a nuestra Api

        val gamesApi = retrofit.create(GamesApi::class.java)

        lifecycleScope.launch {

            try{
                val games = gamesApi.getGames()
                Log.d(Constants.LOGTAG, "Respuesta: $games")

                binding.rvGames.layoutManager = LinearLayoutManager(requireContext())
                binding.rvGames.adapter = GameAdapter(games)
            }
            catch (e: Exception){
                //Manejamos el error de conexión
            }
            finally {
                binding.pbLoading.visibility = View.INVISIBLE
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}