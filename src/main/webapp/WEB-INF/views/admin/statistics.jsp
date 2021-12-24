<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="ko">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Statistics Page</title>
        <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
        crossorigin="anonymous">
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

            #chart-section{
                width: 70%;
                border: 1px dashed black;
            }
            #chart-button{
                background-color: #3278FE;
                color: white;
                width: 100px;
                height: 40px;
                border-radius: 5px;
                text-align: center;
                line-height: 38px;
                font-size: 20px;
                left: 0;
            }
           
        </style>
    </head>

    <body>
        <header id="header">  
            <div id="logout" style="position: absolute;"
            onclick="location.href='${pageContext.servletContext.contextPath}/admin/logout'"
            >Logout</div>
            <nav id="nav">
                <div>
                    <a href="${pageContext.servletContext.contextPath}/admin/member" class="category">Member</a>
                </div>
                <%-- 절대 경로 에서는 맨 앞에 '/' 가 없어야 한다. --%>
                <div>
                    <a href="${pageContext.servletContext.contextPath}/admin/post" class="category">Post</a>
                </div>
                <div>
                    <a href="${pageContext.servletContext.contextPath}/admin/statistics" class="category"
                    style="color: #3278FE;">Statistics</a>
                </div>
                <div>
                    <a href="${pageContext.servletContext.contextPath}/admin/report" class="category">Report</a>
                </div>
                <div>
                    <a href="${pageContext.servletContext.contextPath}/admin/enquiry" class="category">1:1
                        Enquiry</a>
                    </div>
                </nav>
        </header>
        <main>
            <section>
                <div id="static-area">
                    <div>
                        <select name="" class="static-content" onchange="staticInfoF()">
                            <option value="">누적 회원수</option>
                            <option value="">누적 조회수</option>
                            <option value="">누적 게시글</option>
                        </select>
                    </div>

                    <div class="static-info">${cumulativeMembers}</div>
                    <div>
                        <select name="" class="static-content" onchange="staticInfoF()">
                            <option value="">일일 회원수</option>
                            <option value="">일일 조회수</option>
                            <option value="">일일 게시글</option>
                        </select>
                    </div>
                    <div class="static-info">${dailyMembers}</div>
                </div>
            </section>
           
            
                

        </main>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.6.0/chart.min.js"
            integrity="sha512-GMGzUEevhWh8Tc/njS0bDpwgxdCJLQBWG3Z2Ct+JGOpVnEmjvNx6ts4v6A2XJf1HOrtOsfhv3hBKpK9kE5z8AQ=="
            crossorigin="anonymous" referrerpolicy="no-referrer"></script>
        <script src="https://www.gstatic.com/charts/loader.js"></script>
        <!-- <script>
            google.charts.load('current', { packages: ['corechart', 'line'] });
            google.charts.setOnLoadCallback(drawAxisTickColors);
            // 서버 동작 순서 JAVA -> JSTL -> HTML -> Javascript
            function drawAxisTickColors() {
                var data = new google.visualization.DataTable();
                data.addColumn('date', 'X');
                data.addColumn('number', 'readCount');
                data.addRows([
                    <c:forEach items="${listCounts}" var="count" varStatus="vs">
                        <c:choose>
                            <c:when test="${vs.last}">
                                [${count.readCountDate}, ${count.readCount}]
                            </c:when>
                            <c:otherwise>
                                [${count.readCountDate}, ${count.readCount}],
                            </c:otherwise>
                            
                        </c:choose>
                    </c:forEach>
                ])                 
                

                var options = {
                    hAxis: {
                        title: 'Time',
                        textStyle: {
                            color: '#01579b',
                            fontSize: 20,
                            fontName: 'Arial',
                            bold: true,
                        },
                        titleTextStyle: {
                            color: '#01579b',
                            fontSize: 16,
                            fontName: 'Arial',
                            bold: false,
                        }
                    },
                    vAxis: {
                        title: 'Views',
                        textStyle: {
                            color: '#1a237e',
                            fontSize: 24,
                            bold: true
                        },
                        titleTextStyle: {
                            color: '#1a237e',
                            fontSize: 24,
                            bold: true
                        }
                    },
                    colors: ['#a52714', '#097138']
                };
                var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
                chart.draw(data, options);
            }
        </script> -->
        <script>
            const chartArea = document.getElementById("chart_div");
            console.log(chartArea)
            document.getElementById("chart-button").addEventListener("click", function () {
                console.log("a")
                if (chartArea.style.display == "none") {
                    console.log("a")
                    chartArea.style.display = "block";

                } else {
                    chartArea.style.display = "none";

                }
            })

        </script>
        <script>
            const staticInfo = document.getElementsByClassName("static-info");
            const staticContent = document.getElementsByClassName("static-content");
            const option = staticContent[0].options
            const option2 = staticContent[1].options
            function staticInfoF() {

                if (option[0].selected) {
                    staticInfo[0].innerText = ${ cumulativeMembers }
                } else if (option[1].selected) {
                    staticInfo[0].innerText = ${ cumulativeViews }
                } else if (option[2].selected) {
                    staticInfo[0].innerText = ${ cumulativePosts }
                }

                if (option2[0].selected) {
                    staticInfo[1].innerText = ${ dailyMembers }
                } else if (option2[1].selected) {
                    staticInfo[1].innerText = ${ dailyViews }
                } else if (option2[2].selected) {
                    staticInfo[1].innerText = ${ dailyPosts }
                }
            }
        </script>
    </body>

    </html>