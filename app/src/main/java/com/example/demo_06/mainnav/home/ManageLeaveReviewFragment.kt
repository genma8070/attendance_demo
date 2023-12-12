package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentManageLeaveReviewBinding
import com.example.demo_06.mainnav.accountPublic0
import com.example.demo_06.model.HolidayReviewReq
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.HolidayAcquire
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ManageLeaveReviewFragment: BaseFragment<FragmentManageLeaveReviewBinding, PublicViewModel>(
    FragmentManageLeaveReviewBinding::inflate,
    PublicViewModel::class.java,
    true
){

    //
    private fun showAlertDialog(
        workSpot: String,
        personalNo: String,
        startTime: String,
        endTime: String,
        vocationNo: String,
        reason: String
    ) {
        val alertDialog = AlertDialog.Builder(requireContext()).create()

//      タイトルを設定
        alertDialog.setTitle("審査画面")
//      リニアレイアウトと設定
        val linearLayout = LinearLayout(requireContext())
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.setPadding(20, 20, 20, 20)
//      リニアレイアウトの縦と横を設定
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
//
        val workSpotText = SpannableString("現場 : $workSpot\n")
//        workSpotText.setSpan(ForegroundColorSpan(Color.BLACK), 0, workSpotText.length, 0)
//
        val workSpotTextView = TextView(requireContext())
        workSpotTextView.textSize = 20F
        workSpotTextView.text = workSpotText
        linearLayout.addView(workSpotTextView, params)
//
        val personalNoText = SpannableString("社員番号     : $personalNo\n")
//        personalNoText.setSpan(ForegroundColorSpan(Color.BLACK), 0, personalNoText.length, 0)
//
        val personalNoTextView = TextView(requireContext())
        personalNoTextView.textSize = 20F
        personalNoTextView.text = personalNoText
        linearLayout.addView(personalNoTextView, params)

//      開始時刻の内容を設定
        val startText = SpannableString("開始時刻     : $startTime\n")
//        startText.setSpan(ForegroundColorSpan(Color.BLACK), 0, startText.length, 0)
//      開始時刻を設定
        val startTextView = TextView(requireContext())
        startTextView.textSize = 20F
        startTextView.text = startText
        linearLayout.addView(startTextView, params)
//      終了時刻の内容を設定
        val endText = SpannableString("終了時刻     : $endTime\n")
//        endText.setSpan(ForegroundColorSpan(Color.BLACK), 0, endText.length, 0)
//      終了時刻を設定
        val endTextView = TextView(requireContext())
        endTextView.textSize = 20F
        endTextView.text = endText
        linearLayout.addView(endTextView, params)

//
        val vocationNoText = SpannableString("休暇種類     : $vocationNo\n")
//        vocationNoText.setSpan(ForegroundColorSpan(Color.BLACK), 0, vocationNoText.length, 0)
//
        val vocationNoTextView = TextView(requireContext())
        vocationNoTextView.textSize = 20F
        vocationNoTextView.text = vocationNoText
        linearLayout.addView(vocationNoTextView, params)
//
        val reasonText = SpannableString("休暇理由     : $reason\n")
//        reasonText.setSpan(ForegroundColorSpan(Color.BLACK), 0, reasonText.length, 0)
//
        val reasonTextView = TextView(requireContext())
        reasonTextView.textSize = 20F
        reasonTextView.text = reasonText
        linearLayout.addView(reasonTextView, params)

        val rejectReasonText = SpannableString("拒絕理由     : ")
//        reasonText.setSpan(ForegroundColorSpan(Color.BLACK), 0, reasonText.length, 0)
//
        val rejectReasonTextView = TextView(requireContext())
        rejectReasonTextView.textSize = 20F
        rejectReasonTextView.text = rejectReasonText
        linearLayout.addView(rejectReasonTextView, params)
        // 添加一個文字輸入框（EditText）用於拒絕理由
        val rejectReasonEditText = EditText(requireContext())
        rejectReasonEditText.hint = "輸入拒絕理由"
//        rejectReasonEditText.id = View.generateViewId()  // 生成唯一的ID
        // 將文字輸入框添加到LinearLayout中
        linearLayout.addView(rejectReasonEditText, params)

//      詳細画面にlinearLayoutと設定
        alertDialog.setView(linearLayout)

        // 設置"離開"按鈕
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "改修") { dialog, _ ->
            dialog.dismiss()
        }

        // 設置"拒絕"按鈕
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "却下") { dialog, _ ->
            // 獲取拒絕理由
            val rejectReason = rejectReasonEditText.text.toString()

            // 如果拒絕理由為空，顯示提示
            if (rejectReason.isEmpty()) {
                Toast.makeText(requireContext(), "請輸入拒絕理由", Toast.LENGTH_SHORT).show()
            } else {
                // 在這裡處理你的拒絕邏輯，可以將 rejectReason 傳遞到適當的地方
                // 例如，你可以將 rejectReason 傳遞給後端進行處理
                // ...

                dialog.dismiss()
            }
        }

        // 設置"同意"按鈕
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "同意") { dialog, _ ->
            // 顯示"OK"
            Toast.makeText(requireContext(), "OK", Toast.LENGTH_SHORT).show()

            // 在這裡處理你的同意邏輯
            // ...

            dialog.dismiss()
        }

//      詳細画面を表示
        alertDialog.show()
    }

    private fun vacationNoShow(vacationNo: String): String{
        if(vacationNo == "11"){
            return "私用"
        }else if(vacationNo == "12"){
            return "体調不良"
        }else if(vacationNo == "13"){
            return "振替"
        }else{
            return "他"
        }
    }

    override fun initFragment(
        binding: FragmentManageLeaveReviewBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {

//
        binding.tableLayout1.let { it ->
//          テーブルの各列を画面幅に均等に分割
            it.isStretchAllColumns = true
//          ユーザーのアカウントを取得
            val personalNo = accountPublic0
//
            RequestBuilder().getAPI(User::class.java).HolidayReview(HolidayReviewReq(personalNo))
                .enqueue(object : Callback<BaseResponse<List<HolidayAcquire>>> {
                    @SuppressLint("ResourceType", "SetTextI18n")
                    override fun onResponse(
                        call: Call<BaseResponse<List<HolidayAcquire>>>?,
                        response: Response<BaseResponse<List<HolidayAcquire>>>?
                    ) {
//                      検索結果を確認
                        response?.let {
                            if(it.body().data != null) {
                                if(it.body().status == "200"){
//                                  検索結果ありの場合
                                    if(it.body().data != null) {
                                        it.body().data.let {
                                            it?.forEach {item ->
                                                val row = TableRow(requireContext())
                                                row.setPadding(0, 40, 0, 40)
//
                                                val member1 = TextView(requireContext())
                                                member1.textSize = 20F
                                                member1.width = 90
                                                member1.text = item.selectedWorkSpot
                                                row.addView(member1)
//
                                                val member2 = TextView(requireContext())
                                                member2.textSize = 20F
                                                member2.width = 90
                                                member2.text = item.regAuthor
                                                row.addView(member2)
//                                              開始時刻をテーブルに加入
                                                val member3 = TextView(requireContext())
                                                member3.textSize = 20F
                                                member3.width = 90
                                                member3.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay
                                                row.addView(member3)
//                                              終了時刻をテーブルに加入
                                                val member4 = TextView(requireContext())
                                                member4.textSize = 20F
                                                member4.width = 90
                                                member4.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay
                                                row.addView(member4)
//                                              検索結果のクリックで詳細画面を表示
                                                member1.setOnClickListener {
                                                    showAlertDialog(
                                                        item.selectedWorkSpot,
                                                        item.regAuthor,
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                        vacationNoShow(item.vacationNo),
                                                        item.reason
                                                    )
                                                }
                                                member2.setOnClickListener {
                                                    showAlertDialog(
                                                        item.selectedWorkSpot,
                                                        item.regAuthor,
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                        vacationNoShow(item.vacationNo),
                                                        item.reason
                                                    )
                                                }
                                                member3.setOnClickListener {
                                                    showAlertDialog(
                                                        item.selectedWorkSpot,
                                                        item.regAuthor,
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                        vacationNoShow(item.vacationNo),
                                                        item.reason
                                                    )
                                                }
                                                member4.setOnClickListener {
                                                    showAlertDialog(
                                                        item.selectedWorkSpot,
                                                        item.regAuthor,
                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay + " " + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2),
                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay + " " + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2),
                                                        vacationNoShow(item.vacationNo),
                                                        item.reason
                                                    )
                                                }
//                                              テーブルを休暇記録の画面に加入
                                                binding.tableLayout1.addView(row)
                                            }

                                        }
                                    }

                                }

                            }else {
                                Toast.makeText(
                                    requireContext(),
                                    it.body().message,
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<List<HolidayAcquire>>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                    }

                })

        }

    }

}