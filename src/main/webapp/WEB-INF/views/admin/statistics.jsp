<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Admin Statistics Page</title>
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/adminCss.css">
    <style>
        section {
            width: 70%;
            margin: auto;
        }

        #static-area {
            margin-top: 100px;
            width: 650px;
            height: 300px;
            font-weight: bold;
            overflow: visible;
            text-align: center;
        }

        #static-area>div {
            box-sizing: border-box;
            float: left;
            height: 50%;
            /* border: 1px solid black; */
            font-size: 40px;
            line-height: 146px;
            font-size: 40px
        }

        #static-area>div:nth-child(2n-1) {
            width: 40%;
        }

        #static-area>div:nth-child(2n) {
            width: 60%;
        }

        .static-content {
            width: 90%;
            height: 100%;
            font-size: 40px;
            font-weight: bold;
            border: none;
            padding-right: 10px;
        }

        #chart-area{
            display: none;
        }
    </style>
</head>
<body>
	<header id="header">
		<div id="logout">Logout</div>
		<nav id="nav">
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/member"
					class="category" >Member</a>
			</div>
			<%-- 절대 경로 에서는 맨 앞에 '/'가 없어야 한다. --%>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/post"
					class="category">Post</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/statistics"
					class="category" style="color: #3278FE;">Statistics</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/report"
					class="category">Report</a>
			</div>
			<div>
				<a href="${pageContext.servletContext.contextPath}/admin/enquiry"
					class="category">1:1 Enquiry</a>
			</div>
		</nav>
	</header>
	<main>
        <section>
            <div id="static-area">

                <div>
                    <select name="" class="static-content">
                        <option value="">누적 회원수</option>
                        <option value="">누적 조회수</option>
                        <option value="">누적 게시글</option>
                    </select>
                </div>

                <div>99,999</div>
                <div>
                    <select name="" class="static-content">
                        <option value="">일일 회원수</option>
                        <option value="">일일 조회수</option>
                        <option value="">일일 게시글</option>
                    </select>
                </div>
                <div></div>
            </div>
            &nbsp;&nbsp;&nbsp;
            <div class="button" id="chart-button"
                style="background-color: #3278FE; color: white; width: 100px; height: 40px; border-radius: 5px; text-align: center; line-height: 38px; font-size: 20px;">
                상세 통계</div>
            <div id="chart-area">
                <div style="width: 400px; height: 400px;">
                    <canvas id="myChart"></canvas>
                </div>
            </div>

        </section>


    </main>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.min.js"
        integrity="sha512-GMGzUEevhWh8Tc/njS0bDpwgxdCJLQBWG3Z2Ct+JGOpVnEmjvNx6ts4v6A2XJf1HOrtOsfhv3hBKpK9kE5z8AQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
        const ctx = document.getElementById('myChart');
        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['1', '2', '3', '4', '5', '6'],
                datasets: [{
                    label: 'name of chart',
                    data: [12, 19, 3, 5, 2, 3],
                    borderColor: [
                        'blue'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
        const chartArea = document.getElementById("chart-area");
        document.getElementById("chart-button").addEventListener("click", function(){
            console.log("a");
            if (chartArea.style.display == "none") {
                chartArea.style.display = "block";

            }else{
                chartArea.style.display = "none";

            }
        })
    </script>
</body>
</html>