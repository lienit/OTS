window.onload = function (){
    $.ajax({
        url:'/iStatus',
        type: 'POST',
        dataType : 'text',
        success : function (result){
            console.log(result)
            if (result!=""){
                var parse = JSON.parse(result);
                vm.username = parse.userName;
                $('.userStatus1').show();
                $('.userStatus2').hide();
                if (parse.uLevel!="3"){
                    vm.vip = "普通会员";
                }
            }
        }
    });
    $.ajax({
        url:'/getCategory',
        type: 'POST',
        dataType : 'text',
        success : function (result){
            if (result!=""){
                var parse = JSON.parse(result);

                vm.cateList = parse;

            }
        }
    });
    $.ajax({
        url:'/getStore',
        type: 'POST',
        dataType : 'text',
        data:{
            "page" :1,
            "size":4
        },
        success : function (result){
            if (result!=""){
                var parse = JSON.parse(result);

                vm.storeList = parse;

            }
        }
    });

    $.ajax({
        url:'/getNumber',
        type: 'POST',
        dataType : 'text',
        success : function (result){
            if (result!=""){
                vm.sNumber = parseInt(result);
                console.log(vm.sNumber);
            }
        }
    })
}

var vm = new Vue({
    el : '#test03',
    data : function () {
        return {
            activeIndex: '1',
            activeIndex2: '1',
            input3: '',
            select: '',
            username : '',
            vip:'',
            cateList:[{
                cName:""
            }],
            storeList :[{
                gId:"",
                gStoreId:'',
                gCatId : "",
                gName : '',
                gPrice :'',
                gParameter : '',
                gNumber : ''
            }],
            pageSize:4,
            currentPage4: 1,
            sNumber : 0,
            integral:'0',
            src : "/image/common/store.png",
            imgList: [
                {
                    src: '/image/common/1.jpg'
                },
                {
                    src: '/image/common/2.jpg'
                },
                {
                    src: '/image/common/3.jpg'
                }
            ],
            // 图片父容器高度
            bannerHeight: 800,
            // 浏览器宽度
            screenWidth: 0
        };
    },computed: {
        listTemp: function () {
            var list = this.cateList;
            var arrTemp = [];
            var index = 0;
            var sectionCount = 3;
            for (var i = 0; i < list.length; i++) {
                index = parseInt(i / sectionCount);
                if (arrTemp.length <= index) {
                    arrTemp.push([]);
                }
                arrTemp[index].push(list[i]);
            }
            return arrTemp;
        },
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
        handleSizeChange(val) {
            this.pageSize = val;
            this.getStore();
            console.log(`每页 ${val} 条`);
        },
        handleCurrentChange(val) {
            this.currentPage4 = val;
            this.getStore();
            console.log(`当前页: ${val}`);
        },getStore(){
            console.log("sss"+this.currentPage4);
            console.log("sss"+this.pageSize);

            $.ajax({
                url:'/getStore',
                type: 'POST',
                dataType : 'text',
                data:{
                    "page" :this.currentPage4,
                    "size": this.pageSize
                },
                success : function (result){
                    if (result!=""){
                        var parse = JSON.parse(result);
                        console.log(parse);
                        vm.storeList = parse;

                    }
                }
            })
        },
        test(val){
            window.location.href = ("/detailedSto?gId="+val);
        }
        ,handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },sumb(val){
            console.log(val);
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
    }
})