/* 날짜 데이터 연동 */

  // 날짜를 변수화
        const date = new Date();
        console.log(date);
        
        let year = date.getFullYear();    // 2024
        let month = '0' + date.getMonth() + 1;  // 01
            month = month.substring(1) //substring(0,3) > (시작인덱스,표현할단어 수) | substring(1) > 1번인덱스부터 끝까지표시
        let day = '0' + date.getDate();         // 25
            day = day.substring(1);

        let today = year + month + day; // 오늘 날짜는-?

        $.ajax({
            type: "GET",
            url: "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=3e6KfjqGd%2BSHh5QtxpCL6hAh8NyMv%2Bzbbz4btxYu3dmUvLkrW6msC23slFSCHRrGmyHzajC8cTa1OcvCICFebQ%3D%3D&pageNo=1&numOfRows=1000&dataType=json&base_date=" + today + "&base_time=0600&nx=55&ny=127",
            success: function(data){
                console.log(data);
                console.log(data.response.body.items.item[3].obsrValue);

                let item = data.response.body.items.item[3];
                let content = "Date " + item.baseDate + " | Announcement Date " + item.baseTime + " | Temperatures  " + item.obsrValue; 
        
                $('.result').text(content);
            },
            error:function(error){
                console.log(error);
            }
        });







