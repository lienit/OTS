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
        url: '/findByIndentStoreGoods',
        type: 'POST',
        dataType: 'text',
        data:{
            "page" :1,
            "size":4
        },
        success: function (result) {
            vm.tableData=JSON.parse(result);
            console.log(vm.tableData)
        }
    });

    $.ajax({
        url:'/getIndentSize',
        type: 'POST',
        dataType : 'text',
        success : function (result){
            if (result!=""){
                vm.sNumber = parseInt(result);

            }
        }
    })

}


var vm = new Vue({
    el : '#app',
    data : function () {
        return {
            currentPage4: 1,

            activeIndex2: '1',
            input3: '',
            select: '',
            username : '',
            uId:'',
            num3 : '',
            vip:'',
            integral:'0',
            pageSize :4,
            sNumber : 0,
            tableData: [{
                iId : '',
                iUserId : "",
                iSotreId : "",
                iCatId : "",
                iGoodsId : "",
                iAddress : "",
                iMessage : "",
                iPrice : 0,
                iState : 0,
                iDate : '',
                gName : '',
                gPrice : '',
                sName : ''
            }]
        }
    },computed: {
            storeTmp : function (){
                var list = this.storeList;
                var arrTemp = [];
                var index = 0;
                var sectionCount = 4;
                for (var i = 0; i < list.length; i++) {
                    index = parseInt(i / sectionCount);
                    if (arrTemp.length <= index) {
                        arrTemp.push([]);
                    }
                    arrTemp[index].push(list[i]);
                }
                console.log(arrTemp)
                return arrTemp;
            }

    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        quit() {
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
        },
        commit(row){

            var i= JSON.parse(JSON.stringify(row));
            $.ajax({
                url: '/editIndentiState',
                type: 'POST',
                dataType: 'text',
                data : {
                  'iId' : i.iId
                },
                success: function (result) {
                    window.location.reload();
                }
                });
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.getIndent();
                },
        handleCurrentChange(val) {
            this.currentPage4 = val;
            this.getIndent();
        },
        getIndent(){
            console.log(this.pageSize);
            console.log(this.currentPage4);

            $.ajax({
                url: '/findByIndentStoreGoods',
                type: 'POST',
                dataType: 'text',
                data:{
                    "page" : this.currentPage4,
                    "size":this.pageSize
                },
                success: function (result) {
                    vm.tableData=JSON.parse(result);
                    console.log(vm.tableData)
                }
            })
        }


    }
})