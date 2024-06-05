package com.mobile.dufunatask.professional_home.presentation.categories.medication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.dufunatask.R
import com.mobile.dufunatask.auth.presentation.Result
import com.mobile.dufunatask.databinding.FragmentMedicationBinding
import com.mobile.dufunatask.professional_home.presentation.component.TaskAdapter
import com.mobile.dufunatask.utils.Constants.MEDICATION_TAG
import com.mobile.dufunatask.utils.displaySnack
import com.mobile.dufunatask.utils.hide
import com.mobile.dufunatask.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MedicationFragment : Fragment(R.layout.fragment_medication) {

    private val binding by viewBinding(FragmentMedicationBinding::bind)
    private val medicationViewModel : MedicationViewModel by viewModels()

    private val medicationAdapter by lazy { TaskAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() = with(binding){
        medicationList.adapter = medicationAdapter
    }

    private fun setUpObservers() = with(medicationViewModel) {
        medicationTasks.observe(viewLifecycleOwner){
            when(it){
                is Result.Success -> {
                    medicationAdapter.submitList(it.data!!.loginData)
                    Timber.tag(MEDICATION_TAG).d("setUpObservers: %s", it.data.loginData)
                    stopShimmerLoading()
                }
                is Result.Error -> {
                    binding.root.displaySnack(requireContext(), it.message)
                    stopShimmerLoading()
                }
                is Result.Loading -> {
                    startShimmerLoading()
                }
            }
        }
    }

    private fun startShimmerLoading() = with(binding){
        shimmerLoading.shimmerLayout.startShimmer()
    }

    private fun stopShimmerLoading() = with(binding.shimmerLoading.shimmerLayout) {
        stopShimmer()
        hide()
    }
}