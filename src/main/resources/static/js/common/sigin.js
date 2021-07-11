var verify = "";

var ve = new Vue({
    el : '#signIn',
    data : function () {

        return {
            activeIndex: '1',
            activeIndex2: '1',
            input3: '',
            select: '',

            nowTime :'',
            form: {
                email : '',
                authCode  : '',
                username : '',
                password : '',
                phone : ''
            }
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        getCode() {
            var result = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(ve.form.email);


            if (ve.form.email == "" || !result) {

                ve.$message({
                    message: '请填写正确的邮箱！',
                    type: 'warning'
                });
            } else {
                var dateTime =60;
                this.nowTime = new Date().getTime();
                var vc = document.getElementById("eCode");

                var time = setInterval(function () {
                    vc.innerText = dateTime + "秒后重置";
                    $('#eCode').attr("disabled",true)

                    if (dateTime == 0) {
                        vc.innerText = "验证码";
                        $('#eCode').attr("disabled",false)
                        window.clearInterval(time);
                    }
                    dateTime--;
                }, 1000);


                $.ajax({
                    url: '/getCheckCode',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        'mailbox': this.form.email
                    },
                    success: function (result) {
                        ve.veCode = result;

                        console.log(result)
                    }
                });

                console.log("veCode="+this.veCode);
            }
        },

        onSubmit() {

            var number = /^\d{6}$/;
            console.log(number.test(this.form.authCode));
            if (this.form.authCode=="" || this.veCode == "" || !number.test(this.form.authCode)){
                ve.$message({
                    message: '请填写校验码！',
                    type: 'warning'
                });
            }else {

                $.ajax({
                    url : '/userSig',
                    type: 'POST',
                    dataType : 'text',
                    data :{
                        'veCode' : this.veCode,
                        'dateTime' : this.nowTime,
                        'authCode' : this.form.authCode
                    },
                    success: function (result) {
                        console.log("result"+result);
                        console.log(result == "success");
                        if (result == "success"){
                            $("#card1").hide();
                            $("#card2").show();
                        }else if (result == "error"){
                            ve.$message({
                                message: '校验码错误！',
                                type: 'warning'
                            });
                        }else if (result == "overtime"){
                            ve.$message({
                                message: '校验码超时！',
                                type: 'warning'
                            });
                        }
                    }
                })
            }

        }
        ,showEmila() {
            $("#card1").show();
            $("#card2").hide();
        },
        onSignIn() {
            var uPattern = /^[a-zA-Z0-9_-]{4,16}$/;
            var pPattern = /^.*(?=.{6,32})(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*? ]).*$/;
            var mPattern = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\d{8}$/;
            if (ve.form.username == "" || !uPattern.test(ve.form.username)){
                ve.$message({
                    message: '请填写用户名！',
                    type: 'warning'
                });
            }else if (ve.form.password == "" || !pPattern.test(ve.form.password)){
                ve.$message({
                    message: '请填写密码！',
                    type: 'warning'
                });
            }else if (ve.form.phone == "" || !mPattern.test(ve.form.phone)){
                ve.$message({
                    message: '请填写手机号！',
                    type: 'warning'
                });
            }else {
                $.ajax({
                    url : '/userSigin',
                    type: 'POST',
                    dataType : 'text',
                    data :{
                        'emila' : ve.form.email,
                        'username' : ve.form.username,
                        'password' : ve.form.password,
                        'phone' : ve.form.phone
                    },
                    success: function (result) {
                        if (result == "error") {
                            ve.$message({
                                message: '用户名已存在！',
                                type: 'warning'
                            });
                        } else if (result == "error2") {
                            ve.$message({
                                message: '请先进行邮箱验证！',
                                type: 'warning'
                            });
                        } else {
                            ve.$message({
                                message: '注册成功！',
                                type: 'success'
                            });

                            window.location.replace("/home");
                        }
                    }
                });
            }

        }
    }
});