package com.example.tenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.tenantmanagementsystem.databinding.FragmentAddTenantBinding
class AddTenantFragment : Fragment() {
    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // saveButton ← android:id="@+id/saveButton" in fragment_add_tenant.xml
        binding.saveButton.setOnClickListener {
// Read the three EditTexts in fragment_add_tenant.xml
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()
            if (phone.length < 10) {
                binding.phoneEditText.error = "Enter a valid phone number (at least 10 digits)"
                return@setOnClickListener
            }
            if (name.isEmpty() || phone.isEmpty() || rent.isEmpty()) {
// A Fragment is not a Context, so ask for its Activity's
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val tenant = Tenant(name, phone, rent)
// Add to the shared list (the Dashboard and View Tenants read it)
            TenantRepository.addTenant(tenant)
// SAFE ARGS: this function was generated from the <action> in
// nav_graph.xml. Because View Tenants has a <argument> of type
// Tenant, the function REQUIRES a Tenant: it won't compile without one
            val action = AddTenantFragmentDirections
                .actionAddTenantFragmentToViewTenantsFragment(tenant)
// Ask the NavController to follow the action
            findNavController().navigate(action)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}