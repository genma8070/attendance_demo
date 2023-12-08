package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.graphics.Color
import android.os.Bundle
import android.text.InputFilter
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentManageLeaveApplicationBinding
import com.example.demo_06.mainnav.accountPublic0
import com.example.demo_06.model.HolidayAcquireInfo
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserHolidayAcquireRes
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import java.util.regex.Pattern

class ManageLeaveApplicationFragment: BaseFragment<FragmentManageLeaveApplicationBinding, PublicViewModel>(
    FragmentManageLeaveApplicationBinding::inflate,
    PublicViewModel::class.java,
    true
){
    @SuppressLint("SetTextI18n")
    override fun initFragment(
        binding: FragmentManageLeaveApplicationBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {

//      休暇タイプ
        var leaveTypeTemp = "私用"

//      開始日付を選択
        binding.startDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val year0: Int = calendar.get(Calendar.YEAR)
            val month0: Int = calendar.get(Calendar.MONTH)
            val day0: Int = calendar.get(Calendar.DAY_OF_MONTH)

//          開始日付を選択
            val datePickerDialog = DatePickerDialog(
                it.context,
                { _, year0, month0, day0 ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(Calendar.YEAR, year0)
                    selectedCalendar.set(Calendar.MONTH, month0)
                    selectedCalendar.set(Calendar.DAY_OF_MONTH, day0)

                    // 本日以降を選択した場合
                    if(!selectedCalendar.before(Calendar.getInstance())) {
                        val year: String = String.format("%04d", year0)
                        val month: String = String.format("%02d", month0 + 1)
                        val day: String = String.format("%02d", day0)
                        binding.startDate?.text = "$year-$month-$day"
                    }
                },
                year0,
                month0,
                day0
            )

            // 設定可能の日付を本日以降にする
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000 // 限制最小日期為今天

            datePickerDialog.show()
        }

//      開始時間を選択
        binding.startTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            var hourOfDay0: Int = calendar.get(Calendar.HOUR_OF_DAY)
            var minute0: Int = calendar.get(Calendar.MINUTE)

//          ポップアップを設定
            val dialog = AlertDialog.Builder(it.context)
            dialog.setTitle("時間を選択")

//          レイアウトを設定
            val ll = LinearLayout(it.context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.gravity = Gravity.CENTER

//          時間を設定
            val hourPicker = NumberPicker(it.context)
            hourPicker.minValue = 0
            hourPicker.maxValue = 23
            hourPicker.value = hourOfDay0

//          分を設定、間隔は15分
            val minutePicker = NumberPicker(it.context)
            val minuteInterval = 15
            val displayedValues = Array(60 / minuteInterval) { i ->
                String.format("%02d", i * minuteInterval)
            }
//          分を0　15　30　45に設定
            minutePicker.minValue = 0
            minutePicker.maxValue = displayedValues.size - 1
            minutePicker.displayedValues = displayedValues
            minutePicker.value = minute0 / minuteInterval

//          時間の選択
            ll.addView(hourPicker)
//          ：
            ll.addView(TextView(it.context).apply { text = ":" })
//          分の選択
            ll.addView(minutePicker)

//          レイアウトを画面に追加
            dialog.setView(ll)

//          確認ボタンを設定
            dialog.setPositiveButton("確認") { _, _ ->
//              選択した時間と分を取得
                hourOfDay0 = hourPicker.value
                minute0 = minutePicker.value * minuteInterval
//              選択した時間と分を更新
                val selectedTime = String.format("%02d:%02d", hourOfDay0, minute0)
                binding.startTime.text = selectedTime
            }

//          キャンセルボタンを設定
            dialog.setNegativeButton("キャンセル", null)

//          ポップアップを画面に表示
            val alertDialog = dialog.create()
            alertDialog.show()
        }

//      終了日付を選択
        binding.endDate.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            val year0: Int = calendar.get(Calendar.YEAR)
            val month0: Int = calendar.get(Calendar.MONTH)
            val day0: Int = calendar.get(Calendar.DAY_OF_MONTH)

//          終了日付を選択
            val datePickerDialog = DatePickerDialog(
                it.context,
                { _, year0, month0, day0 ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(Calendar.YEAR, year0)
                    selectedCalendar.set(Calendar.MONTH, month0)
                    selectedCalendar.set(Calendar.DAY_OF_MONTH, day0)

                    // 本日以降を選択した場合
                    if(!selectedCalendar.before(Calendar.getInstance())) {
                        val year: String = String.format("%04d", year0)
                        val month: String = String.format("%02d", month0 + 1)
                        val day: String = String.format("%02d", day0)
                        binding.endDate?.text = "$year-$month-$day"
                    }
                },
                year0,
                month0,
                day0
            )

            // 設定可能の日付を本日以降にする
            datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000 // 限制最小日期為今天

            datePickerDialog.show()
        }

//      終了時間を選択
        binding.endTime.setOnClickListener{
            val calendar = Calendar.getInstance()
            var hourOfDay0: Int = calendar.get(Calendar.HOUR_OF_DAY)
            var minute0: Int = calendar.get(Calendar.MINUTE)

//          ポップアップを設定
            val dialog = AlertDialog.Builder(it.context)
            dialog.setTitle("時間を選択")

//          レイアウトを設定
            val ll = LinearLayout(it.context)
            ll.orientation = LinearLayout.HORIZONTAL
            ll.gravity = Gravity.CENTER

//          時間を設定
            val hourPicker = NumberPicker(it.context)
            hourPicker.minValue = 0
            hourPicker.maxValue = 23
            hourPicker.value = hourOfDay0

//          分を設定、間隔は15分
            val minutePicker = NumberPicker(it.context)
            val minuteInterval = 15
            val displayedValues = Array(60 / minuteInterval) { i ->
                String.format("%02d", i * minuteInterval)
            }
//          分を0　15　30　45に設定
            minutePicker.minValue = 0
            minutePicker.maxValue = displayedValues.size - 1
            minutePicker.displayedValues = displayedValues
            minutePicker.value = minute0 / minuteInterval

//          時間の選択
            ll.addView(hourPicker)
//          ：
            ll.addView(TextView(it.context).apply { text = ":" })
//          分の選択
            ll.addView(minutePicker)

//          レイアウトを画面に追加
            dialog.setView(ll)

//          確認ボタンを設定
            dialog.setPositiveButton("確認") { _, _ ->
//              選択した時間と分を取得
                hourOfDay0 = hourPicker.value
                minute0 = minutePicker.value * minuteInterval
//              選択した時間と分を更新
                val selectedTime = String.format("%02d:%02d", hourOfDay0, minute0)
                binding.endTime.text = selectedTime
            }

//          キャンセルボタンを設定
            dialog.setNegativeButton("キャンセル", null)

//          ポップアップを画面に表示
            val alertDialog = dialog.create()
            alertDialog.show()
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

        fun MutableListToJsonArray(list: MutableList<String>): JSONArray {
            val jsonArray = JSONArray()
            list.forEach { jsonArray.put(it) }
            return jsonArray
        }

//      休暇申込をデータベースに追加
        binding.leaveSubmit.setOnClickListener{
            val personalNo = accountPublic0
            val showWorkSpot = null
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
                    RequestBuilder().getAPI(User::class.java).holidayAcquire(HolidayAcquireInfo(personalNo,showWorkSpot,startDate,startTime,endDate,endTime,leaveType,reason))
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
                                                it.body().message,
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
