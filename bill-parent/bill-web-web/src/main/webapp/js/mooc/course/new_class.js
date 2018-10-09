require.config({　　
    baseUrl: 'http://edu.bjhd.gov.cn/', 　　
    paths: {　　　　　　
        "dialog": "js/lib/layer/layer",
        "headerSlideDown": "js/common/headerslideDown",
        "webuploader": "js/lib/webuploader/webuploader",
    }　　
});


require(['headerSlideDown','dialog'], function(aaaa,dialog) {
     
    $('.exeCheck').on('click',function(){
        if($(this).siblings("input[type='checkbox']").is(':checked')){
          $(this).removeClass('cur');
          $(this).siblings("input[type='checkbox']").removeProp('checked')
        }
        else{
          $(this).addClass('cur');
          $(this).siblings("input[type='checkbox']").prop('checked','checked')
        }
    });
    $('.detailsTit a').on('click',function(){
        $(this).addClass('titStyle').siblings().removeClass('titStyle');
        $(".detailsCon>div").eq($(this).index()).show().siblings().hide();
    });
    $('#ul li').on('click',function(){
        $(this).addClass('plattit_style').siblings().removeClass('plattit_style');
        $(this).parent().siblings().children().eq($(this).index()).show().siblings().hide();
    });
    $('.announce span label').on('click',function(){
        $(this).addClass('label_checked').parent().siblings().find('label').removeClass('label_checked');
     });
    $('.ans1 span label').on('click',function(){
        $(this).addClass('label_checked').parent().siblings().find('label').removeClass('label_checked');
     });
    $('.exhibition span label').on('click',function(){
        $(this).addClass('label_checked').parent().siblings().find('label').removeClass('label_checked');
     });
    $('#change li').on('click',function(){
        $(this).addClass('li_click').siblings().removeClass('li_click');
        $('.ti_con').children().eq($(this).index()).show().siblings().hide();
    });
    $('.course_tit a').on('click',function(){
        $(this).addClass('changeA_style').siblings().removeClass('changeA_style');
        $('.course_con>div').eq($(this).index()).show().siblings().hide();
    })
    require(['webuploader'], function(webuploader) {
        // 初始化Web Uploader
        function initUploader(){
            var uploader = webuploader.create({
                // 选完文件后，是否自动上传。
                auto: true,
                // swf文件路径
                swf: 'http://edu.bjhd.gov.cn/js/lib/webuploader/Uploader.swf',
                // 文件接收服务端。
                server: 'http://edu.bjhd.gov.cn/upload/file.html',
                // 选择文件的按钮。可选。
                // 内部根据当前运行是创建，可能是input元素，也可能是flash.
                pick: '#filePicker1',
                // 只允许选择图片文件。
                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
                }
            });


           uploader.on( 'uploadSuccess', function( file , response) { 
                $('.imgbox').empty().html('<img width="330" height="187" src="' + response.url +'">');
               
            });
            uploader.on( 'uploadError', function( file ) {
                
                dialog.msg('上传失败!',{time:1200,icon:1});
            });
            // 上传完毕，重新初始化，解决同一文件，不刷新页面无法多次选取问题
            uploader.on( 'uploadComplete', function( file ) { 
                initUploader();
            });
        }

        initUploader(); 

    });

    //选择系统图片选中效果
        $('.chooseimg_box').find('ul>li').on('click',function(){
            $(this).addClass('imgSel').siblings('li').removeClass('imgSel');
        })
    //图片轮播
    $(function(){
        var i=0;
        var j=0;
        var timer = setInterval(function autoPlay(){
            $('#pic>li').first().appendTo('#pic');
            i++;
            if(i>$('#num>li').length-1){i=0};
            $('#num>li').eq(i).addClass('li').siblings().removeClass('li');
        },2000);
        $('#tit a').on('click',function(){
            $(this).addClass('tapchange').siblings().removeClass('tapchange');
        });
        $('.up1').on('click',function(){
            $('.alertBox').show();
        });
        $('.up2').on('click',function(){
            $('.alertMange').show();
        });
        $('.closeAlert').on('click',function(){
            $('.alertMange').hide();
            $('.alertBox').hide();
        });
    });


    //章节的级联查询
    $('#phase1').change(function(){
        $("#phaseId").val($(this).val());
        getGradeByPhase();
    });

     $('#grade1').change(function(){
        $("#gradeId").val($(this).val());
        getSubjectByGrade();
    });

     //章节学科获取值
    $('#subject1').change(function(){
    $("#subjectId").val($(this).val());
        
    });


     //知识点的级联查询
    $('#phase2').change(function(){
        $("#phaseId").val($(this).val());
        getGradeByPhase2();
    });

     $('#grade2').change(function(){
        $("#gradeId").val($(this).val());
        getSubjectByGrade2();
    });

     //知识点学科获取值
    $('#subject2').change(function(){
    $("#subjectId").val($(this).val());
        
    });



     //点击选择章节
     $("body").on('click','#chooseSection',function(){
        $("#objType").val($(this).attr('name'));
        var phaseId=$("#phaseId").val();
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        if(phaseId==""){
            dialog.msg('请选择学段！',{time:1200,icon:2});
            $('.choosecha_box').hide();
            return;
        }
        if(gradeId==""){
             dialog.msg('请选择年级！',{time:1200,icon:2});
            $('.choosecha_box').hide();
            return;
        }
        if(subjectId==""){
           dialog.msg('请选择学科！',{time:1200,icon:2});
          $('.choosecha_box').hide();
          return;
        }
        $.post("/mooc/course/section.html",{phaseId:phaseId,gradeId:gradeId,subjectId:subjectId},function(msg){
            var msg = jQuery.parseJSON(msg);
            var sectionList=msg.data;
            if(sectionList!=null){
                var html="";
                var a=0;
                $("#sect1").html(html);
                for(var i=0;i<sectionList.length;i++){
                a++;
                html+="<li><div><i class='openbtn'></i><span></span><p>第"+a+"章 "+sectionList[i].name+"</p></div><ul class='selt2'><li><div><i class='openbtn section1'></i><span></span><p name="+sectionList[i].name+" value="+sectionList[i].id+">二级章节</p></div>";
                html+="<ul class='selt3'></ul></li></ul></li>";
                }
                $("#sect1").html(html);
                $('.choosecha_box').show();  
                }else{
                    //alert("该目录中没有章节或知识点！");
                    dialog.msg('该目录中没有章节或知识点！',{time:1200,icon:2});
                }
        });
        
     });


    //点击二级章节查询二级章节内容
    $('body').on('click','.section1',function(){
        var id1 = $(this).parent().find('p').attr('value');
        var name = $(this).parent().find('p').attr('name');
        $("#key1Id").val(id1);
        $("#key1Name").val(name);
        var key1Id = $("#key1Id").val();
        //根据一级章节的id查询二级章节
        $.post("/mooc/course/getSecSection.html",{key1Id:key1Id},function(msg){
            var msg = jQuery.parseJSON(msg);
            var secSectionList=msg.data;
            var html="";
            $(".selt3").html(html);
            for(var i=0;i<secSectionList.length;i++){
                html+="<li><em>"+secSectionList[i].name+"</em><b><input type='checkbox' class='sectchbox' value="+secSectionList[i].id+"><label for='' class='checked'>选择</label></b></li>";
            }
            $(".selt3").html(html);
            
        });
    });


    //点击选择获取二级章节的id和内容
    $("body").on('click','.sectchbox',function(){
        var prop = $(this).prop('checked');
        if(prop==true){
           $(this).attr('checked','checked');
           $("#key2Id").val($(this).val());
           $("#key2Name").val($(this).parent().parent().find('em').html());
        }
        if(prop!=true){
            $(this).attr('checked',null);
        }
    });

    //当点击确定时，获取一级章节以及二级章节的id和内容
    $("body").on('click','#chooseSect',function(){
        var key1Name=$("#key1Name").val();
        var key2Name=$("#key2Name").val();
        dialog.msg("您选择的章节是："+key1Name+"  "+key2Name,{time:1200,icon:1});
        $('.choosecha_box').hide();
    });

    //点击选择知识点
    $("body").on('click','#chooseKnow',function(){
        $("#objType").val($(this).attr('name'));
        var phaseId=$("#phaseId").val();
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        if(phaseId==""){
           dialog.msg('请选择学段！',{time:1200,icon:2});
            $('.choosekonw_box').hide();
            return;
        }
        if(gradeId==""){
            dialog.msg('请选择年级！',{time:1200,icon:2});
            $('.choosekonw_box').hide();
            return;
        }
        if(subjectId==""){
            dialog.msg('请选择学科！',{time:1200,icon:2});
          $('.choosekonw_box').hide();
          return;
        }
        $.post("/mooc/course/knowledge.html",{phaseId:phaseId,subjectId:subjectId},function(msg){
            var msg = jQuery.parseJSON(msg);
            var knowledgeList=msg.data;
            var html="";
            $("#know1").html(html);
            for(var i=0;i<knowledgeList.length;i++){
            html+="<li><div><i class='openbtn'></i><span></span><p>"+knowledgeList[i].name+"</p></div><ul class='selt2'><li><div><i class='openbtn know1'></i><span></span><p name="+knowledgeList[i].name+" value="+knowledgeList[i].id+">二级知识点</p></div>";
            html+="<ul class='selt3'></ul></li></ul></li>";
            }
            $("#know1").html(html);
        $('.choosekonw_box').show();
        });
    });


    //点击二级知识点查询二级知识点内容
    $('body').on('click','.know1',function(){
        var a = $(this).parent().find('p').attr('value');
        var name = $(this).parent().find('p').attr('name');
        $("#key1Id").val(a);
        $("#key1Name").val(name);
        var key1Id = $("#key1Id").val();
        //根据一级知识点的id查询二级知识点
        $.post("/mooc/course/getSecKnow.html",{key1Id:key1Id},function(msg){
            var msg = jQuery.parseJSON(msg);
            var secKnowList=msg.data;
            var html="";
            $(".selt3").html(html);
            for(var i=0;i<secKnowList.length;i++){
                html+="<li><em>"+secKnowList[i].name+"</em><b><input type='checkbox' value="+secKnowList[i].id+" class='knowchobox'><label for=''>选择</label></b></li>";
            }
            $(".selt3").html(html);
            
        });
    });

    //点击选择获取二级知识点的id和内容
    $("body").on('click','.knowchobox',function(){
        var prop = $(this).prop('checked');
        if(prop==true){
           $(this).attr('checked','checked');
           $("#key2Id").val($(this).val());
           $("#key2Name").val($(this).parent().parent().find('em').html());
        }
        if(prop!=true){
            $(this).attr('checked',null);
        }
    });

    //当点击确定时获取值
        $("body").on('click','#choKnow',function(){
            var key1Name=$("#key1Name").val();
            var key2Name=$("#key2Name").val();
            dialog.msg("您选择的知识点是："+key1Name+"  "+key2Name,{time:1200,icon:1});
            $('.choosekonw_box').hide();
    });



    //添加课程
    $('body').on('click','.createCourse',function(){
        var phaseId=$("#phaseId").val();
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        var key1Id = $("#key1Id").val();
        var key2Id = $("#key2Id").val();
        var cover = $('.imgbox').find('img').attr('src');
        $("#cover").val(cover);
        if(phaseId==""){
            dialog.msg('请选择学段！',{time:1200,icon:2});
            return;
        }
        if(gradeId==""){
            dialog.msg('请选择年级！',{time:1200,icon:2});
            return;
        }
        if(subjectId==""){
            dialog.msg('请选择学科！',{time:1200,icon:2});
          return;
        }
        if(key1Id==""){
            dialog.msg('请选择知识点或章节！',{time:1200,icon:2});
          return;
        }
        $("#createCourse").submit();     
    });


    //上传图片
   $(".selectimg").on('click',function(){
        $("#uploadPic").submit();
    });

   //点击选择系统图片，展示默认的系统图片
   $("#filePicker2").on('click',function(){
        $("#sysChooseImg").show();
   });
   //点击系统图片的确定按钮获取图片路径
   $("#selectSysImg").on('click',function(){
    var cover=$(".imgSel").find('img').attr('src');
    $('.imgbox').html("<img width='330' height='187' src='"+cover+"'>");
    $("#sysChooseImg").hide();
   });




$('body').on('click','.openbtn',function(){
    $(this).toggleClass('openbtnbg').parent().siblings('ul').toggle();
    $(this).parent().parent().siblings('li').find('.openbtn').removeClass('openbtnbg').parent().siblings('ul').css('display','none');
    
 })
$('body').on('click','.choosebox_exit',function(){
    $(this).parent().css('display','none')
})
$('body').on('click','.choosebox_exit',function(){
    $(this).parent().parent().css('display','none')
})


});



//根据学段查询年级
    function getGradeByPhase() {
        var phaseId = $("#phaseId").val();
        $.ajax({
            url:"/mooc/course/grade",
            data:{phaseId:phaseId},
            type:"post",
            async:false,
            dataType:"json",
            success:function(msg){  
                var html = "<option>年级</option>";
                $(".grade").html(html);
                var gradeList = msg.data;
                for(var i=0;i<gradeList.length;i++){
                    html += "<option value='"+gradeList[i].id+"'>"+gradeList[i].name+"</option>";
                }
                $(".grade").html(html);     
            },
            error:function(){
                return false;
            }
        });
    }


    function getGradeByPhase2() {
        var phaseId = $("#phaseId").val();
        $.ajax({
            url:"/mooc/course/grade",
            data:{phaseId:phaseId},
            type:"post",
            async:false,
            dataType:"json",
            success:function(msg){  
                var html = "<option>年级</option>";
                $(".grade2").html(html);
                var gradeList = msg.data;
                for(var i=0;i<gradeList.length;i++){
                    html += "<option value='"+gradeList[i].id+"'>"+gradeList[i].name+"</option>";
                }
                $(".grade2").html(html);     
            },
            error:function(){
                return false;
            }
        });
    }


    //根据年级查询学科
    function getSubjectByGrade() {
        var gradeId = $("#gradeId").val();
        $.ajax({
            url:"/mooc/course/subject",
            data:{gradeId:gradeId},
            type:"post",
            async:false,
            dataType:"json",
            success:function(msg){
                var html = "<option>学科</option>";
                $(".subject").html(html);
                var subjectList = msg.data;
                for(var i=0;i<subjectList.length;i++){
                    html += "<option value='"+subjectList[i].id+"'>"+subjectList[i].name+"</option>";
                }
                $(".subject").html(html);       
            },
            error:function(){
                return false;
            }
        });
    }


        function getSubjectByGrade2() {
        var gradeId = $("#gradeId").val();
        $.ajax({
            url:"/mooc/course/subject",
            data:{gradeId:gradeId},
            type:"post",
            async:false,
            dataType:"json",
            success:function(msg){
                var html = "<option>学科</option>";
                $(".subject2").html(html);
                var subjectList = msg.data;
                for(var i=0;i<subjectList.length;i++){
                    html += "<option value='"+subjectList[i].id+"'>"+subjectList[i].name+"</option>";
                }
                $(".subject2").html(html);       
            },
            error:function(){
                return false;
            }
        });
    }



