package com.ute.event_validation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.ute.event_validation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealtimeValidation()

        binding.btnRegister.setOnClickListener {
            handleRegister()
        }
        binding.btnViewDetail.setOnClickListener {
            navigateToDetail()
        }
    }

    private fun setupRealtimeValidation() {
        binding.etName.doAfterTextChanged {
            binding.tilName.error = null
            binding.btnViewDetail.isEnabled = false // Khóa lại nếu có chỉnh sửa
        }
        binding.etEmail.doAfterTextChanged {
            binding.tilEmail.error = null
            binding.btnViewDetail.isEnabled = false
        }
        binding.etPhone.doAfterTextChanged {
            binding.tilPhone.error = null
            binding.btnViewDetail.isEnabled = false
        }
        binding.etMsv.doAfterTextChanged {
            binding.tilMsv.error = null
            binding.btnViewDetail.isEnabled = false
        }
        binding.etClass.doAfterTextChanged {
            binding.tilClass.error = null
            binding.btnViewDetail.isEnabled = false
        }
    }

    private fun handleRegister() {
        val name = binding.etName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val msv = binding.etMsv.text.toString().trim()
        val className = binding.etClass.text.toString().trim()

        var isValid = true

        if (name.isBlank()) {
            binding.tilName.error = "Họ tên không được để trống"
            isValid = false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không hợp lệ"
            isValid = false
        }

        val phoneRegex = "^(03|05|07|08|09)\\d{8}$".toRegex()
        if (!phone.matches(phoneRegex)) {
            binding.tilPhone.error = "Số điện thoại chưa đúng chuẩn VN"
            isValid = false
        }

        if (msv.isBlank()) {
            binding.tilMsv.error = "Mã sinh viên không được để trống"
            isValid = false
        }

        if (className.isBlank()) {
            binding.tilClass.error = "Lớp không được để trống"
            isValid = false
        }

        if (isValid) {
            binding.btnViewDetail.isEnabled = true
            val snackbar = Snackbar.make(binding.root, "Đăng ký thành công!", Snackbar.LENGTH_LONG)
            snackbar.setAction("XEM CHI TIẾT") {
                navigateToDetail() // GỌI HÀM Ở ĐÂY
            }
            snackbar.show()
        }
    }
    private fun navigateToDetail() {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("EXTRA_NAME", binding.etName.text.toString().trim())
            putExtra("EXTRA_EMAIL", binding.etEmail.text.toString().trim())
            putExtra("EXTRA_PHONE", binding.etPhone.text.toString().trim())
            putExtra("EXTRA_MSV", binding.etMsv.text.toString().trim())
            putExtra("EXTRA_CLASS", binding.etClass.text.toString().trim())
        }
        startActivity(intent)
    }
}