window.onload = function (){
    $.ajax({
        url:'/iStatus',
        type: 'POST',
        dataType : 'text',
        success : function (result){
            console.log(result)
            if (result!=""){
                var parse = JSON.parse(result);
                ve.form.username = parse.userName;
                ve.form.email = parse.uEmail;



            }else {
                window.location.replace("/home");
            }
        }
    })
}

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
                phone : '',
                wEmail:'',
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
        },getCode1() {
            var result = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(ve.form.wEmail);


            if (ve.form.wEmail == "" || !result) {

                ve.$message({
                    message: '请填写正确的邮箱！',
                    type: 'warning'
                });
            } else {
                var dateTime =60;
                this.nowTime = new Date().getTime();
                var vc = document.getElementById("eCode1");

                var time = setInterval(function () {
                    vc.innerText = dateTime + "秒后重置";
                    $('#eCode1').attr("disabled",true)

                    if (dateTime == 0) {
                        vc.innerText = "验证码";
                        $('#eCode1').attr("disabled",false)
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

            if (this.form.authCode=="" && this.veCode == "" && !number.test(this.form.authCode)){
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
                            ve.form.authCode = "";
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

        },
        quit(){
            $.ajax({
                url: '/quit',
                type: 'POST',
                dataType: 'text',
                success: function (result) {
                    if (result=="success"){
                        vm.username = "";
                        window.location.reload();
                    }
                }
            })
        }
        ,showEmila() {
            $("#card1").show();
            $("#card2").hide();
        },
        onSignIn() {
            var result = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(ve.form.email);

            var number = /^\d{6}$/;

            if (this.form.authCode=="" && this.veCode == "" && !number.test(this.form.authCode)){
                ve.$message({
                    message: '请填写校验码！',
                    type: 'warning'
                });
            }else if (ve.form.wEmail == "" || !result) {

                ve.$message({
                    message: '请填写正确的邮箱！',
                    type: 'warning'
                });
            }else {
                $.ajax({
                    url : '/openEmail',
                    type: 'POST',
                    dataType : 'text',
                    data :{
                        'email' : ve.form.wEmail,
                        'veCode' : this.veCode,
                        'dateTime' : this.nowTime,
                        'authCode' : this.form.authCode
                    },
                    success: function (result) {
                        if (result == "error") {
                            ve.$message({
                                message: '验证码不正确！',
                                type: 'warning'
                            });
                        } else {
                            ve.$message({
                                message: '修改成功！',
                                type: 'success'
                            });

                            window.location.replace("/userInfo");
                        }
                    }
                });
            }

        }
    }
});