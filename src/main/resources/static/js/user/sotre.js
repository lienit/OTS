window.onload = function () {
    $.ajax({
        url: '/iStatus',
        type: 'POST',
        dataType: 'text',
        success: function (result) {

            if (result != "") {
                var parse = JSON.parse(result);
                vm.username = parse.userName;
                vm.uId = parse.uId;
                $('.userStatus1').show();
                $('.userStatus2').hide();
                if (parse.uLevel != "3") {
                    vm.vip = "普通会员";
                }
            }
        }
    });

    $.ajax({
        url: '/getParticular',
        type: 'POST',
        dataType: 'text',
        success: function (result) {
            var parse = JSON.parse(result);
            vm.goodsList = parse;
            console.log(vm.goodsList);
        }
    });
    $.ajax({
        url: '/getStoreInfo',
        type: 'POST',
        dataType: 'text',
        success: function (result) {
            var parse = JSON.parse(result);
            vm.storeList = parse;
            console.log(vm.storeList);
        }
    })
}
var vm = new Vue({
    el : '#app',
    data : {
        activeIndex: '1',
        activeIndex2: '1',
        input3: '',
        select: '',
        username : '',
        uId:'',
        vip:'',
        num3 : '',
        integral:'0',
        dialogVisible: 'false',
        src : "/image/common/store.png",
        goodsList :{
            gId:"",
            gStoreId:'',
            gCatId : "",
            gName : '',
            gPrice :'',
            gParameter : '',
            gNumber : ''
        },
        numIndent: 1,
        indentList : {
            iUserId : "",
            iSotreId : "",
            iCatId : "",
            iGoodsId : "",
            iAddress : "",
            iMessage : "",
            iPrice : 0,
            iState : 0
        },
        storeList:{
            sId : '',
            sName : '',
            sUserId : '',
        },
        form : {

        },
        visible: false,
        tableData: [{
            aId:"",
            aPostcode: "",
            aConsignee: '',
            aAddress: '',
            isDefault : "",
            aPhone : ''
        }],
        coutAdd : '',
        choiceData: [{
            aId:"",
            aPostcode: "",
            aConsignee: '',
            aAddress: '',
            isDefault : "",
            aPhone : ''
        }],
        dialogVisible: false,

    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(done) {
            this.$confirm('确认关闭？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        },edit(row){
            // vm.choiceData[0] = JSON.parse(JSON.stringify(row));
            this.$set(vm.choiceData,0, JSON.parse(JSON.stringify(row)));
            vm.visible=false;
        },
        updatePrice(value){
            console.log(value);
            vm.indentList.iPrice = vm.goodsList.gPrice * vm.numIndent;
            console.log(vm.indentList.iPrice);
        }
        , quit() {
            $.ajax({
                url: '/quit',
                type: 'POST',
                dataType: 'text',
                success: function (result) {
                    if (result == "success") {
                        vm.username = "";
                        window.location.reload();
                    }
                }
            })
        },submitForm(){
            if (this.username==""){
                vm.$message({
                    message: '请先登录或注册！',
                    type: 'warning'
                });
            }else {
                vm.dialogVisible = true;
                vm.indentList.iPrice = vm.goodsList.gPrice * vm.numIndent;
                console.log(vm.indentList.iPrice);
                $.ajax({
                    url : '/findAddress',
                    type : 'POST',
                    dataType : 'text',
                    success : function (req){
                        vm.tableData= JSON.parse(req);
                        vm.coutAdd = vm.tableData.length;
                        vm.getAddress();
                    }
                })

            }
        },
        getAddress(){
            for (var i = 0; i < vm.coutAdd; i++){
                if (vm.tableData[i].isDefault){
                    // vm.choiceData[0] = vm.tableData[i];
                    this.$set(vm.choiceData,0, vm.tableData[i]);
                }
            }
        },
        commitIndet(){
            vm.indentList.iCatId = vm.goodsList.gCatId;
            vm.indentList.iAddress = vm.choiceData[0].aAddress;
            vm.indentList.iSotreId = vm.storeList.sId;
            vm.indentList.iUserId = vm.uId;
            vm.indentList.iGoodsId = vm.goodsList.gId;
            $.ajax({
                url : '/commitIndet',
                type : 'POST',
                dataType : 'text',
                data: {
                    "iUserId" : vm.indentList.iUserId,
                    "iSotreId" : vm.indentList.iSotreId,
                    "iCatId" : vm.indentList.iCatId,
                    "iGoodsId" : vm.indentList.iGoodsId,
                   "iAddress": vm.indentList.iAddress,
                    "iMessage" : vm.indentList.iMessage,
                    "iPrice" : vm.indentList.iPrice,
                    "iState" : vm.indentList.iState
                },
                success : function (req){
                    vm.$message({
                        message: '购买成功！！',
                        type: 'success'
                    });
                    var time = setInterval(function () {
                        vm.dialogVisible = false;
                        window.location.reload();
                        window.clearInterval(time);

                    }, 500);



                }
            })
        }
    }

})