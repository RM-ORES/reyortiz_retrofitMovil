package com.example.retrofitMovil.ui.pantallaActualizar

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.reyortiz_retrofitmovil.databinding.ActivityActualizarBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActualizarActivity : AppCompatActivity() {
    private val viewModel: ActualizarViewModel by viewModels()
    private lateinit var binding : ActivityActualizarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActualizarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}