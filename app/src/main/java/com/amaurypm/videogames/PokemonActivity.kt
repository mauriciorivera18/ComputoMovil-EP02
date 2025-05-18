package com.amaurypm.videogames

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.amaurypm.videogames.data.remote.PokemonApi
import com.amaurypm.videogames.databinding.ActivityPokemonBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonBinding

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mediaPlayer = MediaPlayer.create(this, R.raw.pokemonsong)

        mediaPlayer.start()

        mediaPlayer.isLooping = true

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonApi = retrofit.create(PokemonApi::class.java)

        lifecycleScope.launch {
            try{

                val pokemon = pokemonApi.getPokemonDetail("25")

                binding.tvPokemon.text = pokemon.name.replaceFirstChar {
                    it.uppercase()
                }


                Glide.with(this@PokemonActivity)
                    .load(pokemon.sprites.other.officialArtwork.frontDefault)
                    .into(binding.ivPokemon)

            }catch(e: Exception){

            }
        }

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.pause()
    }

    override fun onRestart() {
        super.onRestart()
        mediaPlayer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}