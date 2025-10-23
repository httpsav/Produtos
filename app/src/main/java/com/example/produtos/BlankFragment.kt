package com.example.produtos

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.produtos.databinding.FragmentAgendamentoBinding
import java.text.SimpleDateFormat
import java.util.*

class AgendamentoFragment : Fragment() {

    private var _binding: FragmentAgendamentoBinding? = null
    private val binding get() = _binding!!
    private val calendario = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgendamentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Campo de Data
        binding.etData.setOnClickListener {
            val ano = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(requireContext(), { _, year, month, day ->
                calendario.set(year, month, day)
                val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                binding.etData.setText(formato.format(calendario.time))
            }, ano, mes, dia).show()
        }

        // Campo de Hora
        binding.etHora.setOnClickListener {
            val hora = calendario.get(Calendar.HOUR_OF_DAY)
            val minuto = calendario.get(Calendar.MINUTE)

            TimePickerDialog(requireContext(), { _, h, m ->
                calendario.set(Calendar.HOUR_OF_DAY, h)
                calendario.set(Calendar.MINUTE, m)
                val formato = SimpleDateFormat("HH:mm", Locale.getDefault())
                binding.etHora.setText(formato.format(calendario.time))
            }, hora, minuto, true).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
