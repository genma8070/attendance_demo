package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveApplicationBinding
import com.example.demo_06.mainnav.accountPublic
import com.example.demo_06.model.HolidayAcquireInfo
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserHolidayAcquireRes
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import java.util.regex.Pattern

class EmployeeLeaveApplicationFragment: BaseFragment<FragmentEmployeeLeaveApplicationBinding, PublicViewModel>(
    FragmentEmployeeLeaveApplicationBinding::inflate,
    PublicViewModel::class.java,
    true
){
    @SuppressLint("SetTextI18n")
    override fun initFragment(
        binding: FragmentEmployeeLeaveApplicationBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {

//      休暇タイプ
        var leaveTypeTemp = "私用"

//      休暇理由を50字以内に制限
        binding.reason.filters = arrayOf<InputFilter>(Filter, InputFilter.LengthFilter(50))

//      開始日付を選択
        binding.startDate.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year0: Int = calendar.get(Calendar.YEAR)
            val month0: Int = calendar.get(Calendar.MONTH)
            val day0: Int = calendar.get(Calendar.DAY_OF_MONTH)
//          選択した日付を表示する
            DatePickerDialog(it.context,
                { _, year0, month0, day0->
                    val year: String = String.format("%04d",year0)
                    val month: String = String.format("%02d",month0+1)
                    val day: String = String.format("%02d",day0)
                    binding.startDate?.text = "$year-$month-$day"
                }, year0, month0, day0
            ).show()
        }

//      開始時間を選択
        binding.startTime.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val hourOfDay0: Int = calendar.get(Calendar.HOUR)
            val minute0: Int = calendar.get(Calendar.MINUTE)
//          選択した時間を表示する
            TimePickerDialog(it.context,
                { _, hourOfDay0, minute0 ->
                    val month: String = String.format("%02d",hourOfDay0)
                    val day: String = String.format("%02d",minute0)
                    binding.startTime?.text = "$month:$day"
                }, hourOfDay0, minute0, true
            ).show()
        }

//      終了日付を選択
        binding.endDate.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val year0: Int = calendar.get(Calendar.YEAR)
            val month0: Int = calendar.get(Calendar.MONTH)
            val day0: Int = calendar.get(Calendar.DAY_OF_MONTH)
//          選択した日付を表示する
            DatePickerDialog(it.context,
                { _, year0, month0, day0->
                    val year: String = String.format("%04d",year0)
                    val month: String = String.format("%02d",month0+1)
                    val day: String = String.format("%02d",day0)
                    binding.endDate?.text = "$year-$month-$day"
                }, year0, month0, day0
            ).show()
        }

//      終了時間を選択
        binding.endTime.setOnClickListener{
            val calendar: Calendar = Calendar.getInstance()
            val hourOfDay0: Int = calendar.get(Calendar.HOUR)
            val minute0: Int = calendar.get(Calendar.MINUTE)
//          選択した時間を表示する
            TimePickerDialog(it.context,
                { _, hourOfDay0, minute0 ->
                    val month: String = String.format("%02d",hourOfDay0)
                    val day: String = String.format("%02d",minute0)
                    binding.endTime?.text = "$month:$day"
                }, hourOfDay0, minute0, true
            ).show()
        }

//      休暇タイプをタイプ1に表示
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
//      休暇タイプをタイプ2に表示
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
//      休暇タイプをタイプ3に表示
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
//      休暇タイプをタイプ4に表示
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

//      休暇申込をデータベースに追加
        binding.leaveSubmit.setOnClickListener{
            val personalNo = accountPublic
            val startDate = binding.startDate.text.toString()
            val startTime = binding.startTime.text.toString()
            val endDate = binding.endDate.text.toString()
            val endTime = binding.endTime.text.toString()
            val leaveType = leaveTypeTemp
            val reason = binding.reason.text.toString()
//          休暇申込の再確認
            AlertDialog.Builder(activity)
                .setTitle("申込確認")
                .setMessage("本当に申込ますか")
                .setPositiveButton("確認"){_, _ ->
//                  APIに接続し、休暇申込をデータベースに追加
                    RequestBuilder().getAPI(User::class.java).holidayAcquire(HolidayAcquireInfo(personalNo,startDate,startTime,endDate,endTime,leaveType,reason))
                        .enqueue(object : Callback<BaseResponse<UserHolidayAcquireRes>> {
                            override fun onResponse(
                                call: Call<BaseResponse<UserHolidayAcquireRes>>?,
                                response: Response<BaseResponse<UserHolidayAcquireRes>>?
                            ) {
                                response?.let {
                                    if(it.body().data != null) {
//                                      休暇申込が成功したかを確認
                                        if(it.body().status == "200"){
//                                          休暇申込が成功のメッセージを表示
                                            Toast.makeText(
                                                requireContext(),
                                                "${it.body().message}",
                                                Toast.LENGTH_SHORT).show()
//                                          入力内容を初期化する
                                            binding.startDate.text = "日付を選択"
                                            binding.startTime.text = "時間を選択"
                                            binding.endDate.text = "日付を選択"
                                            binding.endTime.text = "時間を選択"
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
//                                      休暇申込が失敗のメッセージを表示
                                        Toast.makeText(
                                            requireContext(),
                                            it.body().message,
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
    val Filter = InputFilter { source, _, _, _, _, _ ->
        val p = Pattern.compile(".+")
        val m = p.matcher(source.toString())
        if (!m.matches()) "" else null
    }

}