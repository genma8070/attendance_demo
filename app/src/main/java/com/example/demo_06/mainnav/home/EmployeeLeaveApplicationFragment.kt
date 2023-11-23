package com.example.demo_06.mainnav.home

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveApplicationBinding
import com.example.demo_06.databinding.FragmentEmployeeNavBinding
import com.example.demo_06.mainnav.accountPublic
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import java.util.Calendar

class EmployeeLeaveApplicationFragment: BaseFragment<FragmentEmployeeLeaveApplicationBinding, PublicViewModel>(
    FragmentEmployeeLeaveApplicationBinding::inflate,
    PublicViewModel::class.java,
    true
){
    override fun initFragment(
        binding: FragmentEmployeeLeaveApplicationBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {
//        viewModel!!.testValue.observe(requireActivity()){
//            binding.value.text=it
//        }
//        binding.btnValue.setOnClickListener {
////            requireActivity().findNavController(R.id.app_nav).navigate(R.id.action_mainNavFragment_to_otherFragment)
//            viewModel.testValue.value = "你好 homeViewModel"
//        }
//        binding.btnPubValue.setOnClickListener {
//            publicViewModel?.apply {
//                this.testValue.value="home改變了public的值"
//            }
//        }
//        binding.datePicker.setOnClickListener {
//
//        }

//        binding.btnLogout.setOnClickListener{
//            findNavController().navigate(
//                com.example.demo_06.R.id.loginFragment,
//                null,
//                NavOptions.Builder().setPopUpTo(com.example.demo_06.R.id.manageNavFragment,true).build())
//        }

        binding.startDate.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
//          選択した日付を表示する
            DatePickerDialog(it.context,
                { view, year, month, day ->
                    val datetime = "$year-$month-$day"
                    binding.startDate?.setText(datetime)
                }, year, month, day
            ).show()
        }

        binding.startTime.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val hourOfDay: Int = calendar.get(Calendar.HOUR)
            val minute: Int = calendar.get(Calendar.MINUTE)
//          選択した時間を表示する
            TimePickerDialog(it.context,
                { view, hourOfDay, minute ->
                    val datetime = "$hourOfDay:$minute"
                    binding.startTime?.setText(datetime)
                }, hourOfDay, minute, true
            ).show()
        }

        binding.endDate.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year: Int = calendar.get(Calendar.YEAR)
            val month: Int = calendar.get(Calendar.MONTH)
            val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
//          選択した日付を表示する
            DatePickerDialog(it.context,
                { view, year, month, day ->
                    val datetime = "$year-$month-$day"
                    binding.endDate?.setText(datetime)
                }, year, month, day
            ).show()
        }

        binding.endTime.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val hourOfDay: Int = calendar.get(Calendar.HOUR)
            val minute: Int = calendar.get(Calendar.MINUTE)
//          選択した時間を表示する
            TimePickerDialog(it.context,
                { view, hourOfDay, minute ->
                    val datetime = "$hourOfDay:$minute"
                    binding.endTime?.setText(datetime)
                }, hourOfDay, minute, true
            ).show()
        }

        binding.holidayType1.setOnClickListener{
            binding.holidayType1.setBackgroundColor(Color.parseColor("#0000FF"))
            binding.holidayType1.setTextColor(Color.parseColor("#FFFFFF"))
            binding.holidayType2.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType2.setTextColor(Color.parseColor("#000000"))
            binding.holidayType3.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType3.setTextColor(Color.parseColor("#000000"))
            binding.holidayType4.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType4.setTextColor(Color.parseColor("#000000"))
        }
        binding.holidayType2.setOnClickListener{
            binding.holidayType1.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType1.setTextColor(Color.parseColor("#000000"))
            binding.holidayType2.setBackgroundColor(Color.parseColor("#0000FF"))
            binding.holidayType2.setTextColor(Color.parseColor("#FFFFFF"))
            binding.holidayType3.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType3.setTextColor(Color.parseColor("#000000"))
            binding.holidayType4.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType4.setTextColor(Color.parseColor("#000000"))
        }
        binding.holidayType3.setOnClickListener{
            binding.holidayType1.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType1.setTextColor(Color.parseColor("#000000"))
            binding.holidayType2.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType2.setTextColor(Color.parseColor("#000000"))
            binding.holidayType3.setBackgroundColor(Color.parseColor("#0000FF"))
            binding.holidayType3.setTextColor(Color.parseColor("#FFFFFF"))
            binding.holidayType4.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType4.setTextColor(Color.parseColor("#000000"))
        }
        binding.holidayType4.setOnClickListener{
            binding.holidayType1.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType1.setTextColor(Color.parseColor("#000000"))
            binding.holidayType2.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType2.setTextColor(Color.parseColor("#000000"))
            binding.holidayType3.setBackgroundColor(Color.parseColor("#EEEEEE"))
            binding.holidayType3.setTextColor(Color.parseColor("#000000"))
            binding.holidayType4.setBackgroundColor(Color.parseColor("#0000FF"))
            binding.holidayType4.setTextColor(Color.parseColor("#FFFFFF"))
        }

        binding.leaveSubmit.setOnClickListener{
//            var startDate = binding.startDate.text.toString()
//            var startTime = binding.startTime.text.toString()

            Toast.makeText(
                requireContext(),
                accountPublic,//需要取得(account的值)
                Toast.LENGTH_SHORT).show()

        }

    }

}