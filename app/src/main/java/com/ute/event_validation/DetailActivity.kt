package com.ute.event_validation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ute.event_validation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Nhận dữ liệu từ Intent do trang Đăng ký gửi sang
        val name = intent.getStringExtra("EXTRA_NAME") ?: ""
        val email = intent.getStringExtra("EXTRA_EMAIL") ?: ""
        val phone = intent.getStringExtra("EXTRA_PHONE") ?: ""
        val msv = intent.getStringExtra("EXTRA_MSV") ?: ""
        val className = intent.getStringExtra("EXTRA_CLASS") ?: ""

        // 2. Điền từng dữ liệu vào đúng 5 ô chỉ đọc trên giao diện
        binding.edtDetailName.setText(name)
        binding.edtDetailEmail.setText(email)
        binding.edtDetailPhone.setText(phone)
        binding.edtDetailMsv.setText(msv)
        binding.edtDetailClass.setText(className)

        // 3. Xử lý nút QUAY LẠI ĐĂNG KÝ
        binding.btnBack.setOnClickListener {
            finish() // Đóng màn hình này để lùi về trang trước
        }
    }
}