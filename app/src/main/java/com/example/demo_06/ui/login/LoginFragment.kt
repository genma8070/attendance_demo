package com.example.demo_06.ui.login

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentLoginBinding
import com.example.demo_06.model.LoginInfo
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserLoginRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class LoginFragment: BaseFragment<FragmentLoginBinding,ViewModel>(
    FragmentLoginBinding::inflate,
    null
) {
    override fun initFragment(
        binding: FragmentLoginBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {
//        binding.loginBtnEmployee.setOnClickListener{
//            findNavController().navigate(
//                R.id.employeeNavFragment,
//                null,
//                NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
//        }
//        binding.loginBtnManage.setOnClickListener{
//            findNavController().navigate(
//                R.id.manageNavFragment,
//                null,
//                NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
//        }

        binding.accountInput.filters = arrayOf<InputFilter>(Filter,InputFilter.LengthFilter(9))
        binding.passwordInput.filters = arrayOf<InputFilter>(Filter,InputFilter.LengthFilter(20))

        binding.loginBtn.setOnClickListener{
            var account = binding.accountInput.text.toString()
            var password = binding.passwordInput.text.toString()

//            RequestBuilder().getAPI(User::class.java).login(LoginInfo("A003","123123"))
            RequestBuilder().getAPI(User::class.java).login(LoginInfo(account,password))
//                .enqueue(object : Callback<BaseResponse<UserLoginRes>> {
                .enqueue(object : Callback<BaseResponse<UserLoginRes>> {
                    override fun onResponse(
                        call: Call<BaseResponse<UserLoginRes>>?,
                        response: Response<BaseResponse<UserLoginRes>>?
                    ) {
                        response?.let {
                            if(it.body().data != null) {
                                Log.e("TAG","onResponse:${it.body().data.toString()}")
                                Log.e("TAG","onResponse:${it.code()}")
//                                Log.e("TAG","onResponse:${it.message()}")
//                                Log.e("TAG","onResponse(code):${it.body().data?.password.toString()}")
//                                Log.e("TAG","onResponse(code):${it.body().data?.account.toString()}")
//                                Log.e("TAG","onResponse(message):${it.body().data?.name.toString()}")
//                                Log.e("TAG","onResponse:${it.body().data}")
                                Toast.makeText(
                                    requireContext(),
                                    "${it.body().message}",
                                    Toast.LENGTH_SHORT).show()

                                if(it.body().status == "200"){
//                                  アカウントを次のページに伝達用
                                    val bundle = Bundle()
                                    bundle.putString("account", it.body().data?.personalNo)

                                    if(it.body().data?.appAuthority == 1) {

                                        findNavController().navigate(
                                            R.id.employeeNavFragment,
                                            bundle,
                                            NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
                                    }
                                    else {
                                        findNavController().navigate(
                                            R.id.manageNavFragment,
                                            bundle,
                                            NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
                                    }
                                }

                            }else {
                                Toast.makeText(
                                    requireContext(),
                                    "${it.body().message}",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<UserLoginRes>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                    }

                })
//            findNavController().navigate(
//                R.id.mainNavFragment,
//                null,
//                NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
        }

    }

//  入力制限（英語と数字しか入力できる）
    val Filter = InputFilter { source, start, end, dest, dstart, dend ->
        val p = Pattern.compile("[0-9a-zA-Z]+")
        val m = p.matcher(source.toString())
        val s = p.matcher(source[0].toString())
        if (!s.matches())
            Toast.makeText(
                requireContext(),
                "英語と数字しか入力できません",
                Toast.LENGTH_SHORT).show()
        if (!m.matches()) "" else null
    }

}