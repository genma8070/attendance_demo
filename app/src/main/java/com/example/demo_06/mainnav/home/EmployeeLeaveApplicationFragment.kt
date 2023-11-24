package com.example.demo_06.mainnav.home

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveApplicationBinding
import com.example.demo_06.databinding.FragmentEmployeeNavBinding
import com.example.demo_06.mainnav.accountPublic
import com.example.demo_06.model.HolidayAcquireInfo
import com.example.demo_06.model.LoginInfo
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserHolidayAcquireRes
import com.example.demo_06.network.res.UserLoginRes
import com.example.demo_06.ui.app.MainActivity
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.regex.Pattern

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

        var leaveTypeTemp = "私用"

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

        binding.reason.filters = arrayOf<InputFilter>(Filter,InputFilter.LengthFilter(50))
//        binding.reason.filters

        binding.startDate.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year0: Int = calendar.get(Calendar.YEAR)
            val month0: Int = calendar.get(Calendar.MONTH)
            val day0: Int = calendar.get(Calendar.DAY_OF_MONTH)

//          選択した日付を表示する
            DatePickerDialog(it.context,
                { view, year0, month0, day0->
                    val year: String = String.format("%04d",year0)
                    val month: String = String.format("%02d",month0+1)
                    val day: String = String.format("%02d",day0)
                    binding.startDate?.setText(year+"-"+month+"-"+day)
                }, year0, month0, day0
            ).show()
        }

        binding.startTime.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val hourOfDay0: Int = calendar.get(Calendar.HOUR)
            val minute0: Int = calendar.get(Calendar.MINUTE)
//          選択した時間を表示する
            TimePickerDialog(it.context,
                { view, hourOfDay0, minute0 ->
                    val month: String = String.format("%02d",hourOfDay0)
                    val day: String = String.format("%02d",minute0)
                    binding.startTime?.setText(month+":"+day)
                }, hourOfDay0, minute0, true
            ).show()
        }

        binding.endDate.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year0: Int = calendar.get(Calendar.YEAR)
            val month0: Int = calendar.get(Calendar.MONTH)
            val day0: Int = calendar.get(Calendar.DAY_OF_MONTH)

//          選択した日付を表示する
            DatePickerDialog(it.context,
                { view, year0, month0, day0->
                    val year: String = String.format("%04d",year0)
                    val month: String = String.format("%02d",month0+1)
                    val day: String = String.format("%02d",day0)
                    binding.endDate?.setText(year+"-"+month+"-"+day)
                }, year0, month0, day0
            ).show()
        }

        binding.endTime.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val hourOfDay0: Int = calendar.get(Calendar.HOUR)
            val minute0: Int = calendar.get(Calendar.MINUTE)
//          選択した時間を表示する
            TimePickerDialog(it.context,
                { view, hourOfDay0, minute0 ->
                    val month: String = String.format("%02d",hourOfDay0)
                    val day: String = String.format("%02d",minute0)
                    binding.endTime?.setText(month+":"+day)
                }, hourOfDay0, minute0, true
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
            leaveTypeTemp = "私用"
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
            leaveTypeTemp = "体調不良"
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
            leaveTypeTemp = "振替"
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
            leaveTypeTemp = "他"
        }

        binding.leaveSubmit.setOnClickListener{
            var personalNo = accountPublic
            var startDate = binding.startDate.text.toString()
            var startTime = binding.startTime.text.toString()
            var endDate = binding.endDate.text.toString()
            var endTime = binding.endTime.text.toString()
            var leaveType = leaveTypeTemp
            var reason = binding.reason.text.toString()

            AlertDialog.Builder(getActivity())
                .setTitle("申込確認")
                .setMessage("本当に申込ますか")
                .setPositiveButton("確認"){_, _ ->

                    RequestBuilder().getAPI(User::class.java).holidayAcquire(HolidayAcquireInfo(personalNo,startDate,startTime,endDate,endTime,leaveType,reason))
                        .enqueue(object : Callback<BaseResponse<UserHolidayAcquireRes>> {
                            override fun onResponse(
                                call: Call<BaseResponse<UserHolidayAcquireRes>>?,
                                response: Response<BaseResponse<UserHolidayAcquireRes>>?
                            ) {
                                response?.let {
                                    if(it.body().data != null) {
//                                Toast.makeText(
//                                    requireContext(),
//                                    "${it.body().message}",
//                                    Toast.LENGTH_SHORT).show()

                                        if(it.body().status == "200"){
                                            Toast.makeText(
                                                requireContext(),
                                                "${it.body().message}",
                                                Toast.LENGTH_SHORT).show()

                                            binding.startDate.setText("日付を選択")
                                            binding.startTime.setText("時間を選択")
                                            binding.endDate.setText("日付を選択")
                                            binding.endTime.setText("時間を選択")
                                            binding.holidayType1.setBackgroundColor(Color.parseColor("#0000FF"))
                                            binding.holidayType1.setTextColor(Color.parseColor("#FFFFFF"))
                                            binding.holidayType2.setBackgroundColor(Color.parseColor("#EEEEEE"))
                                            binding.holidayType2.setTextColor(Color.parseColor("#000000"))
                                            binding.holidayType3.setBackgroundColor(Color.parseColor("#EEEEEE"))
                                            binding.holidayType3.setTextColor(Color.parseColor("#000000"))
                                            binding.holidayType4.setBackgroundColor(Color.parseColor("#EEEEEE"))
                                            binding.holidayType4.setTextColor(Color.parseColor("#000000"))
                                            leaveTypeTemp = "私用"
                                            binding.reason.setText("")
                                        }

                                    }else {
                                        Toast.makeText(
                                            requireContext(),
                                            "${it.body().message}",
                                            Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }

                            override fun onFailure(call: Call<BaseResponse<UserHolidayAcquireRes>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                            }

                        })
                }
                .setNeutralButton("キャンセル", null)
                .show()

        }

    }

    //  入力制限
    val Filter = InputFilter { source, start, end, dest, dstart, dend ->
        val p = Pattern.compile(".+")
        val m = p.matcher(source.toString())
        if (!m.matches()) "" else null
    }

}