<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Dessert_Safrr</title>

    <link href="${pageContext.request.contextPath}/static/lib/fonts/css/all.min.css" rel="stylesheet">
    <!-- Bootstrap 5 -->
    <link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- jQuery -->
    <script src="${pageContext.request.contextPath}/static/lib/jquery/jquery-3.6.0.min.js"></script>

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/css/home.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/static/lib/fonts/Geometria-Light/style.css" rel="stylesheet" type="text/css">



    <script type="module" src="${pageContext.request.contextPath}/static/js/bundle.js"></script>

    <!-- Custom JS  ?????-->
<%--    <script src="${pageContext.request.contextPath}/static/js/util/locale.js"></script>--%>
<%--    <script type="module" src="${pageContext.request.contextPath}/static/js/util/locale.js"></script>--%>

</head>

<header>
    <div class="page-top" style="width: 100%; height: 20px; background-color: #1b6d85;"></div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">



                <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=go_to_home_page_command"><svg height="55" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
                                                                                                viewBox="0 0 441.64 403.36" style="enable-background:new 0 0 441.64 403.36;" xml:space="preserve">
	        <style type="text/css">.st0{fill: #e1791a;}</style><g>
                    <path class="st0" d="M185.42,305.24c-19.85,3.96-39.47,8.41-59.3,11.68c-19.29,3.18-38.72,6.12-58.21,7.41
		            c-23.13,1.52-35.5-10.78-35.42-33.77c0.03-8.95-0.92-16.56-7.41-23.8c-6.42-7.16-4.26-16.27-1.13-24.73
		        c2.86-7.72,6.57-15.31,8.12-23.3c2.54-13.03,0.16-24.4-11.75-33.58c-12.4-9.55-11.8-35.75-3.47-48.26
		c3.65-5.48,5.06-12.41,7.7-18.59c4.65-10.89,7.83-22.9,14.72-32.19c5.3-7.14,14.73-13.26,23.43-15.43
		c11.89-2.96,20.51-9.33,28.56-17.24c11.13-10.95,24.95-13.88,39.73-17c14.38-3.04,27.85-7.84,42.34-1.41c1.53,0.68,4.01-1,6.09-1.2
		c11.88-1.14,23.76-2.94,35.65-2.99c8.89-0.04,17.2,1.69,25.84-3.61c4.57-2.81,12.5-2.72,18.02-1.05
		c18.65,5.65,36.89,12.61,55.31,19.01c2.54,0.88,5.25,2.19,7.79,2.01c29.56-2.11,49.09,14.28,66.46,34.94
		c2.46,2.92,5.31,5.74,8.52,7.78c24.14,15.4,34.66,48.09,28.79,76c-3.54,16.83-2.27,35.35,0.4,52.56
		c2.62,16.85,5.07,31.48-7.32,46.35c-5.49,6.6-4.99,18.1-7.25,27.37c-5.69,23.34-20.59,40.14-39.52,53.83
		c-32.89,23.78-71.14,33.1-110.46,38.5c-18.77,2.58-37.86,3.16-56.83,3.87c-14.68,0.55-19.43-4.79-19.42-19.52
		C185.43,348.27,185.42,327.67,185.42,305.24z M191.05,326.05c0,14.15-0.04,27.45,0.01,40.75c0.05,11.7,2.63,14.85,14.14,14.58
		c15.34-0.36,30.77-0.85,45.98-2.73c28.91-3.58,57.65-7.59,84.8-19.66c33.56-14.91,59.75-36.32,71.77-71.91
		c2.04-6.03,2.83-12.48,4.3-19.23c-10.14-1.54-17.18-0.94-22.15,8c-2.41,4.33-7.38,7.84-11.97,10.29
		c-12.15,6.48-24.46,12.79-37.19,18.03c-14.39,5.93-28.91,12.95-44.05,15.38C262.68,325.02,228.52,331.85,191.05,326.05z
		 M418.11,177.33c-0.97-0.72-1.95-1.44-2.92-2.16c-10.25,7.43-20.51,14.84-30.73,22.3c-11.12,8.11-21.39,17.83-33.49,24.06
		c-21.31,10.97-43.33,20.89-65.85,29.11c-11.77,4.29-25.21,5.25-37.94,5.5c-15.58,0.31-31.22-1.64-46.82-2.74
		c-4.75-0.33-6.51,1.6-6.32,6.24c0.41,9.58,0.83,19.17,0.7,28.74c-0.09,7.28,2.6,10.54,10.19,9.54c22.96-3.05,45.89-6.36,68.91-8.78
		c28.46-2.99,53.64-15.34,79.11-26.64c11.42-5.07,22.15-12.08,32.29-19.44c9.16-6.65,17.55-14.52,25.47-22.64
		C423.02,207.76,417.49,191.98,418.11,177.33z M215.3,167.68c-8.3,0.56-14.22,0.52-19.99,1.43c-44.86,7.03-89.75,13.9-134.51,21.53
		c-26.91,4.59-24.17-1.93-24.2,27.12c0,0.6-0.01,1.2,0.01,1.8c0.33,14.26,15.87,27.54,30.14,25.32
		c33.61-5.23,67.11-11.18,100.79-15.85c12.95-1.79,22.87-5.23,25.59-19.43c0.43-2.23,2.28-4.17,3.4-6.29
		C202.34,192.31,208.12,181.3,215.3,167.68z M75.63,119.15c-16.16-2.34-29.63,1.14-41.91,9.05c-23.46,15.1-25,40.54-3.5,58.39
		c2.24,1.86,5.93,3.21,8.77,2.94c19.79-1.86,37.36-8.46,57-11.44c22.31-3.39,46.73-3.07,68.89-7.33
		c13.12-2.52,26.15-5.47,40.71-8.54c-3.69-3.16-6.06-4.95-8.16-7.02c-9.75-9.63-20.93-18.29-28.66-29.33
		c-6.58-9.39-13.8-9.72-22.93-8.95c0.41,9.19,1.38,17.84,1.01,26.43c-0.29,6.84-1.64,14.95-9.88,16.35
		c-21.5,3.66-40.57-2.59-56.49-17.23c-2.74-2.52-3.86-7.29-4.69-11.23C75.03,127.56,75.63,123.59,75.63,119.15z M257.51,114.99
		c0.25,0.82,0.49,1.64,0.74,2.45c5.88,1.21,11.85,2.12,17.63,3.7c8.1,2.22,12.33-0.71,17.29-7.62
		c14.41-20.07,32.69-35.19,59.43-35.2c13.99-0.01,20,5.05,23.17,19.11c2.86,12.71,4.07,26.23,3.21,39.22
		c-1.34,20.04-11.7,26.94-30.38,25.99c-5.11-0.26-10.19-1.24-15.3-1.38c-1.61-0.05-3.27,1.54-4.91,2.38
		c1.39,1.47,2.72,4.15,4.19,4.23c18.17,0.91,33.35,9.44,48.63,17.95c1.95,1.09,5.3,1.62,7.11,0.7c6.95-3.54,13.91-7.28,20.19-11.86
		c12.98-9.47,16.63-21.59,11.96-37.1c-2.41-8-3.97-16.48-7.77-23.79c-3.24-6.24-8.81-11.46-13.96-16.52
		c-5.03-4.94-10.98-8.94-16.25-13.66c-8.42-7.55-15.36-18.46-25.17-22.36c-16.15-6.41-34.11-8.34-51.7-12.25
		C305.96,83.73,282.91,100.19,257.51,114.99z M37.55,278.52c-3.72,24.44-0.02,32.69,24.91,38.46c3.44,0.8,7.24,0.46,10.82,0.09
		c8.68-0.88,17.39-1.76,25.99-3.21c27.59-4.63,55.15-9.39,82.65-14.5c2.86-0.53,7.31-3.62,7.46-5.76
		c0.76-10.89,0.35-21.85,0.35-31.59c-15.45,0.91-28.24,1.6-41.02,2.43c-6.61,0.43-13.34,0.45-19.78,1.79
		C98.97,272.47,68.83,277.12,37.55,278.52z M239.28,159.02c1.49,7.26,3.45,20.27,7.06,32.81c1.83,6.35,6.3,12.11,10.23,17.68
		c4.07,5.76,10.11,7.78,16.73,4.76c7.02-3.2,14.23-6.43,20.41-10.93c13.96-10.16,25.87-22.31,30.04-39.77
		c2.69-11.27-3.29-21.36-14.4-25.44c-10.43-3.83-20.77-8.02-31.44-11.05c-6.75-1.92-14.33-3.66-21.02-2.52
		C244.92,126.59,239.64,136.58,239.28,159.02z M251.63,106.41c0-3.2,0.07-5.6-0.01-7.99c-0.74-22.64-14.14-32.38-36.12-26.46
		c-9.28,2.5-18.61,4.83-27.98,6.95c-18.95,4.27-27.1,25.8-16.31,41.69c8.38,12.35,18.84,22.53,30.85,30.96
		c6.73,4.73,14.87,7.65,22.66,10.66c4.88,1.89,8.43,0.07,7.67-6.02c-1.53-12.33,2.96-23.05,11.93-30.6
		C251.32,119.71,252.68,113.51,251.63,106.41z M418.59,220.14c-3.12,2.63-5.69,4.58-8,6.8c-2.37,2.28-4.28,5.03-6.72,7.21
		c-26.62,23.74-57.8,40.03-91.58,50.27c-35.98,10.9-72.83,18.97-110.87,19.56c-2.82,0.04-7.52,3.43-7.89,5.8
		c-0.5,3.15,1.8,9.38,3.92,9.86c10.05,2.29,20.51,4.54,30.69,4.04c14.04-0.69,27.9-4.69,41.95-5.79c26.14-2.04,51.19-6.8,73.4-21.71
		c2.9-1.95,6.51-3.1,9.95-3.96c14.17-3.54,25.69-10.85,35.43-21.76c3.16-3.54,8.88-4.95,13.6-6.94c3.46-1.46,8.06-1.29,10.64-3.55
		C423.69,250.75,425.97,234.49,418.59,220.14z M222.32,167.51c-3.75,6.29-7.37,11.39-9.98,16.95c-7.2,15.35-15.07,30.53-20.56,46.5
		c-4.12,11.98,0.56,16.05,13.3,16.64c14.09,0.66,28.15,2.04,42.23,3.1c20.15,1.51,38.89-3.46,56.26-13.14
		c9.87-5.5,9.58-11.9-0.27-17.81c-7.54-4.52-14.79-7.4-24.33-2.43c-14.44,7.51-25.2,4.29-31.29-8.28
		c-4.79-9.88-9.14-20.25-11.61-30.88C234.04,169.46,230.07,166.49,222.32,167.51z M291.88,125.37c4.92,1.51,7.88,2.08,10.53,3.3
		c10.73,4.96,22.86,8.3,25.68,22.46c0.38,1.89,3.37,3.98,5.57,4.64c4.28,1.29,8.82,1.84,13.3,2.32c17.94,1.9,26.71-6.08,26.24-24.12
		c-0.27-10.15-1.22-20.29-1.67-30.44c-0.65-14.96-13.82-24.64-28.21-19.54c-8.96,3.18-17.07,8.66-25.8,12.57
		C304.96,102.16,300.69,114.35,291.88,125.37z M34.3,233.73c-2.54,5.05-4.58,8.39-5.96,11.97c-3.84,9.98,0.64,22.82,9.65,24.77
		c8.08,1.75,16.74,2.04,25.03,1.44c12.52-0.91,24.93-3.2,37.39-4.81c5.67-0.73,11.39-1.15,17.06-1.89
		c13.06-1.71,26.15-3.3,39.15-5.38c9.14-1.46,18.22-3.39,27.16-5.75c1.72-0.45,3.96-4.02,3.7-5.84c-0.73-5.18-2.62-10.2-4.14-15.58
		c-30.62,4.32-61.33,8.13-91.84,13.12C71,249.14,50.79,254.08,34.3,233.73z M159.42,35.93c-4.05-1.45-8.52-1.19-12.39,0.69
		c-2.96,1.44-5.85,2.93-8.45,4.82c-6.29,4.56-12.57,9.43-17.72,15.16c-4.88,5.43-9.57,11.68-12.05,18.4
		c-2.87,7.79,0.93,12.03,8.73,12.47c10.83,0.61,17.76,6.66,22.16,15.9c3.57,7.51,10.41,7.48,16.93,7.22
		c6.97-0.28,3.78-6.21,4.38-9.79c1.66-10.01,5.42-18.05,15.9-22.1c2.6-1.01,6.06-6.57,5.26-8.36c-4.65-10.5-9.9-20.85-16.16-30.48
		C164.81,38,162.32,36.97,159.42,35.93z M234.94,63.89c4.06,0.2,7.95,2.14,9.94,5.69c0.4,0.71,0.86,1.37,1.43,1.93
		c7.74,7.66,11.13,16.86,11.54,27.39c0.25,6.39,2.39,7.43,8.55,4.75c14.54-6.31,22.79-18.55,29.8-31.24
		c10.32-18.68,2.61-32.86-18.51-36.8c-6.22-1.16-12.61-1.77-18.61-3.61c-15.63-4.78-30.65,3.79-29.78,22.65
		c0.14,2.98,0.66,7.48,2.53,8.49C232.73,63.63,233.79,63.84,234.94,63.89z M142.99,133.4c-2.52-9.68-4.07-19.74-7.81-28.93
		c-4.04-9.94-15.61-13.95-24.76-8.38c-9.33,5.67-17.61,13.17-25.88,20.41c-8.71,7.63-7.81,18.73,2.2,24.54
		c8.21,4.77,16.86,9.06,25.79,12.28C134.76,161.32,141.2,156.7,142.99,133.4z M298.26,207.93c5.73,5.44,11.6,11.34,17.88,16.78
		c1.73,1.5,4.9,2.72,6.99,2.25c20.15-4.56,37-15.22,52.33-28.62c4.3-3.76,4.19-6.71-0.31-9.99c-13.32-9.71-28.51-13.62-44.74-14.16
		c-1.52-0.05-3.51,1.09-4.58,2.29C316.52,186.9,307.38,197.48,298.26,207.93z M31.06,121.99c11.07-3.11,21.35-8.2,31.72-8.37
		c13.61-0.23,23.04-5.94,32.51-14.22c11.73-10.25,12.21-10.17-1-18.35c-6-3.71-13.01-9.03-19.22-8.61
		C51.44,74,29.9,96.33,31.06,121.99z M222.95,35.98c-14.54-1.75-30.86-4.42-47.02,4.45c3.7,8.93,7.79,16.99,10.33,25.5
		c2.3,7.71,5.9,7.25,12.65,5.69C217.58,67.33,231.4,60.39,222.95,35.98z M101.61,80.43c-0.94-18.87,16.25-23.66,23.06-36.09
		c-10.9-1.79-35.18,11.48-38.36,20.86c-0.44,1.3,0.15,3.72,1.17,4.59C91.22,73.01,95.35,75.8,101.61,80.43z"/>
                    <g><g><path class="st0" d="M124.22,74.95c2.33,1.94,4.67,3.88,7,5.82c0.39,0.33,0.8,0.55,1.33,0.55c0.46,0,1.01-0.2,1.33-0.55
				        c0.63-0.69,0.81-1.98,0-2.65c-2.33-1.94-4.67-3.88-7-5.82c-0.39-0.33-0.8-0.55-1.33-0.55c-0.46,0-1.01,0.2-1.33,0.55
				        C123.59,72.99,123.42,74.28,124.22,74.95L124.22,74.95z"/></g></g><g><g>
                    <path class="st0" d="M137.81,61.1c2.18,2.89,4.56,5.61,7.2,8.09c0.72,0.67,1.95,0.76,2.65,0c0.67-0.73,0.76-1.93,0-2.65
				c-1.24-1.17-2.44-2.38-3.57-3.65c-0.55-0.62-1.09-1.24-1.62-1.88c-0.27-0.33-0.54-0.66-0.8-0.99c-0.07-0.09-0.15-0.19-0.22-0.28
				c-0.13-0.17-0.06-0.06,0.03,0.04c-0.16-0.18-0.29-0.38-0.43-0.57c-0.31-0.41-0.61-0.72-1.12-0.86c-0.45-0.12-1.05-0.07-1.44,0.19
				C137.71,59.03,137.18,60.26,137.81,61.1L137.81,61.1z"/>
                </g></g><g><g>
                    <path class="st0" d="M155.34,47.04c1.73,2.61,3.66,5.1,5.75,7.42c0.66,0.73,1.99,0.72,2.65,0c0.72-0.78,0.7-1.87,0-2.65
				c-0.98-1.09-1.92-2.22-2.82-3.37c-0.16-0.2,0.21,0.27,0.05,0.07c-0.05-0.07-0.11-0.14-0.16-0.21c-0.11-0.14-0.21-0.28-0.32-0.42
				c-0.22-0.3-0.44-0.6-0.66-0.9c-0.43-0.6-0.85-1.22-1.27-1.84c-0.54-0.82-1.68-1.24-2.57-0.67C155.2,45,154.76,46.17,155.34,47.04
				L155.34,47.04z"/></g></g><g><g>
                    <path class="st0" d="M140.48,90.95c2,1.68,3.82,3.57,5.42,5.63c0.27,0.35,0.9,0.55,1.33,0.55c0.46,0,1.01-0.2,1.33-0.55
				c0.33-0.36,0.57-0.83,0.55-1.33c-0.02-0.17-0.04-0.33-0.07-0.5c-0.09-0.32-0.25-0.59-0.48-0.83c-1.61-2.06-3.42-3.95-5.42-5.63
				c-0.39-0.33-0.8-0.55-1.33-0.55c-0.46,0-1.01,0.2-1.33,0.55C139.84,89,139.68,90.28,140.48,90.95L140.48,90.95z"/>
                </g></g><g><g>
                    <path class="st0" d="M154.67,77.34c0.81,0.45,1.59,0.95,2.32,1.52c-0.13-0.1-0.25-0.2-0.38-0.29c1.03,0.8,1.97,1.71,2.8,2.71
				c0.29,0.35,0.89,0.55,1.33,0.55c0.46,0,1.01-0.2,1.33-0.55c0.33-0.36,0.57-0.83,0.55-1.33c-0.02-0.52-0.22-0.92-0.55-1.33
				c-1.52-1.83-3.42-3.38-5.5-4.52c-0.86-0.47-2.08-0.24-2.57,0.67C153.54,75.65,153.76,76.84,154.67,77.34L154.67,77.34z"/>
                </g></g><g><g>
                    <path class="st0" d="M169.64,63.11c0.38,0.31,0.74,0.64,1.08,1c0.16,0.17,0.31,0.34,0.46,0.51c0.09,0.1,0.17,0.2,0.25,0.3
				c0.22,0.27-0.28-0.38-0.02-0.03c0.58,0.78,1.05,1.62,1.43,2.51c-0.06-0.15-0.13-0.3-0.19-0.45c0.2,0.49,0.37,0.98,0.51,1.49
				c0.26,0.95,1.34,1.62,2.31,1.31c0.97-0.31,1.59-1.29,1.31-2.31c-0.74-2.7-2.32-5.21-4.49-6.99c-0.4-0.32-0.79-0.55-1.33-0.55
				c-0.46,0-1.01,0.2-1.33,0.55C169.01,61.15,168.83,62.45,169.64,63.11L169.64,63.11z"/>
                </g>
                </g>
                    <g>
                        <g>
                            <path class="st0" d="M248.16,41.46c0,2.21,0,4.42,0,6.62c0,0.98,0.86,1.92,1.88,1.88c1.02-0.05,1.88-0.82,1.88-1.88
				c0-2.21,0-4.42,0-6.62c0-0.98-0.86-1.92-1.88-1.88C249.02,39.63,248.16,40.41,248.16,41.46L248.16,41.46z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M268.64,44.95c-0.52,2.86-1.28,5.66-2.28,8.39c-0.34,0.92,0.38,2.09,1.31,2.31
				c1.06,0.24,1.95-0.32,2.31-1.31c1-2.73,1.76-5.53,2.28-8.39c0.17-0.96-0.26-2.07-1.31-2.31
				C270.03,43.44,268.83,43.92,268.64,44.95L268.64,44.95z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M287.99,49.8c-0.16,0.72-0.34,1.44-0.56,2.14c-0.12,0.38-0.24,0.75-0.38,1.12
				c-0.07,0.19-0.14,0.37-0.21,0.55c-0.02,0.05-0.23,0.57-0.13,0.32c0.1-0.24-0.12,0.27-0.14,0.32c-0.08,0.18-0.16,0.36-0.25,0.54
				c-0.16,0.33-0.33,0.66-0.5,0.99c-0.36,0.67-0.76,1.33-1.18,1.96c-0.54,0.82-0.19,2.11,0.67,2.57c0.94,0.5,1.99,0.2,2.57-0.67
				c1.77-2.67,3.05-5.71,3.73-8.84c0.21-0.96-0.28-2.07-1.31-2.31C289.35,48.27,288.21,48.77,287.99,49.8L287.99,49.8z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M246.42,57.8c-0.01,0.61-0.04,1.23-0.1,1.84c-0.02,0.19-0.04,0.38-0.06,0.56c-0.04,0.4,0.06-0.37-0.01,0.06
				c-0.05,0.3-0.09,0.61-0.15,0.91c-0.23,1.23-0.55,2.45-0.97,3.63c-0.33,0.93,0.37,2.09,1.31,2.31c1.05,0.24,1.96-0.32,2.31-1.31
				c0.9-2.56,1.37-5.29,1.42-8c0.02-0.98-0.88-1.92-1.88-1.88C247.26,55.97,246.44,56.75,246.42,57.8L246.42,57.8z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M266.18,63.97c-1.39,3.11-2.26,6.34-2.61,9.73c-0.1,0.98,0.93,1.92,1.88,1.88c1.1-0.05,1.77-0.83,1.88-1.88
				c0.02-0.22,0.05-0.43,0.07-0.65c0.05-0.42-0.05,0.3,0.02-0.15c0.06-0.38,0.12-0.75,0.19-1.13c0.13-0.69,0.29-1.38,0.47-2.07
				c0.18-0.68,0.39-1.36,0.63-2.02c0.13-0.36,0.26-0.72,0.4-1.07c0.06-0.15,0.12-0.3,0.18-0.45c-0.17,0.4,0.11-0.23,0.14-0.3
				c0.4-0.89,0.29-2.06-0.67-2.57C267.93,62.86,266.61,63.01,266.18,63.97L266.18,63.97z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M283.7,68.4c-0.08,0.2-0.15,0.39-0.23,0.59c-0.03,0.07-0.29,0.7-0.11,0.28c-0.16,0.39-0.33,0.77-0.51,1.15
				c-0.33,0.71-0.68,1.42-1.05,2.11c-0.77,1.43-1.63,2.81-2.56,4.14c-0.57,0.8-0.17,2.12,0.67,2.57c0.96,0.51,1.96,0.19,2.57-0.67
				c2-2.84,3.6-5.92,4.85-9.16c0.35-0.92-0.39-2.1-1.31-2.31C284.93,66.84,284.08,67.42,283.7,68.4L283.7,68.4z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M267.19,86.51c-0.01,0.3-0.02,0.59-0.04,0.89c-0.01,0.17-0.03,0.34-0.05,0.51c0,0.04-0.01,0.08-0.01,0.13
				c-0.03,0.21-0.02,0.17,0.02-0.12c0.04,0.07-0.06,0.42-0.08,0.5c-0.02,0.12-0.05,0.25-0.07,0.37c-0.06,0.29-0.12,0.58-0.2,0.87
				c-0.15,0.57-0.33,1.14-0.54,1.69c-0.05,0.12-0.1,0.23-0.14,0.35c0.18-0.51-0.02,0.02-0.07,0.13c-0.12,0.27-0.26,0.53-0.4,0.79
				c-0.46,0.87-0.25,2.08,0.67,2.57c0.87,0.46,2.07,0.25,2.57-0.67c1.31-2.47,2.04-5.2,2.09-8c0.02-0.98-0.88-1.92-1.88-1.88
				C268.03,84.68,267.21,85.46,267.19,86.51L267.19,86.51z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M187.08,99.78c2.16,2.89,4.46,5.68,6.91,8.32c0.67,0.72,1.98,0.73,2.65,0c0.71-0.77,0.71-1.88,0-2.65
				c-1.15-1.25-2.28-2.52-3.37-3.83c-0.53-0.63-1.05-1.27-1.56-1.92c-0.14-0.18-0.28-0.35-0.42-0.53c-0.06-0.08-0.13-0.16-0.18-0.24
				c0.09,0.13,0.14,0.18,0.02,0.02c-0.27-0.36-0.55-0.72-0.81-1.08c-0.3-0.41-0.61-0.72-1.12-0.86c-0.45-0.12-1.05-0.07-1.44,0.19
				C186.98,97.71,186.45,98.94,187.08,99.78L187.08,99.78z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M209.73,87.47c1.32,3.48,2.47,7.02,3.46,10.61c0.26,0.95,1.34,1.62,2.31,1.31c0.97-0.31,1.59-1.29,1.31-2.31
				c-0.99-3.59-2.14-7.13-3.46-10.61c-0.35-0.91-1.28-1.64-2.31-1.31C210.15,85.46,209.36,86.49,209.73,87.47L209.73,87.47z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M231.26,82.99c0.35,3.38,0.69,6.76,1.04,10.14c0.1,0.97,0.79,1.92,1.88,1.88c0.93-0.04,1.98-0.83,1.88-1.88
				c-0.35-3.38-0.69-6.76-1.04-10.14c-0.1-0.97-0.79-1.92-1.88-1.88C232.21,81.15,231.16,81.94,231.26,82.99L231.26,82.99z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M194.97,121.46c1.68,2.68,4.05,4.94,6.86,6.41c0.87,0.46,2.08,0.25,2.57-0.67c0.46-0.86,0.25-2.08-0.67-2.57
				c-0.62-0.33-1.23-0.69-1.8-1.1c-0.14-0.1-0.27-0.2-0.41-0.3c-0.26-0.19,0.28,0.22,0.1,0.08c-0.07-0.05-0.13-0.1-0.2-0.16
				c-0.27-0.22-0.54-0.45-0.8-0.69c-0.5-0.47-0.98-0.97-1.42-1.49c-0.11-0.13-0.21-0.26-0.32-0.39c0.4,0.46,0.02,0.02-0.07-0.1
				c-0.22-0.3-0.42-0.61-0.62-0.92c-0.52-0.83-1.7-1.23-2.57-0.67C194.81,119.43,194.41,120.57,194.97,121.46L194.97,121.46z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M217.13,113.02c0.29,2.8,0.58,5.6,0.86,8.39c0.1,0.97,0.79,1.92,1.88,1.88c0.93-0.04,1.98-0.83,1.88-1.88
				c-0.29-2.8-0.58-5.6-0.86-8.39c-0.1-0.97-0.79-1.92-1.88-1.88C218.08,111.18,217.03,111.97,217.13,113.02L217.13,113.02z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M238,106.82c0,2.8,0,5.59,0,8.39c0,0.98,0.86,1.92,1.88,1.88c1.02-0.05,1.88-0.82,1.88-1.88
				c0-2.8,0-5.59,0-8.39c0-0.98-0.86-1.92-1.88-1.88C238.86,104.99,238,105.77,238,106.82L238,106.82z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M214.94,139.06c1.91,2.81,4.51,5.14,7.58,6.61c0.46,0.22,0.94,0.33,1.44,0.19c0.43-0.12,0.91-0.46,1.12-0.86
				c0.44-0.84,0.27-2.11-0.67-2.57c-0.68-0.32-1.33-0.69-1.96-1.1c-0.31-0.2-0.62-0.42-0.92-0.64c-0.05-0.04-0.33-0.25-0.08-0.06
				c0.25,0.2-0.04-0.04-0.08-0.06c-0.15-0.12-0.29-0.24-0.43-0.36c-0.55-0.48-1.08-0.99-1.57-1.53c-0.25-0.28-0.49-0.57-0.73-0.86
				c-0.15-0.18,0.25,0.34,0.05,0.07c-0.06-0.08-0.11-0.15-0.17-0.23c-0.12-0.17-0.24-0.34-0.36-0.51c-0.55-0.81-1.67-1.25-2.57-0.67
				C214.81,137.02,214.35,138.19,214.94,139.06L214.94,139.06z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M93.33,126.16c2.34,2.01,5.22,3.18,8.3,3.32c0.98,0.04,1.92-0.89,1.88-1.88c-0.05-1.05-0.82-1.83-1.88-1.88
				c-0.3-0.01-0.62-0.09-0.92-0.08c0.31,0.04,0.36,0.05,0.15,0.02c-0.08-0.01-0.15-0.02-0.23-0.04c-0.15-0.03-0.3-0.06-0.45-0.09
				c-0.59-0.13-1.16-0.3-1.72-0.52c-0.44-0.17,0.31,0.15-0.03-0.01c-0.14-0.07-0.28-0.13-0.42-0.2c-0.28-0.14-0.55-0.29-0.81-0.44
				c-0.27-0.16-0.52-0.33-0.77-0.51c-0.06-0.05-0.13-0.09-0.19-0.14c0.23,0.18,0.26,0.2,0.1,0.08c-0.12-0.1-0.24-0.2-0.36-0.3
				c-0.74-0.64-1.93-0.78-2.65,0C92.69,124.21,92.54,125.47,93.33,126.16L93.33,126.16z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M102.68,113.51c1.32,2.41,3.26,4.44,5.56,5.92c0.83,0.53,2.11,0.2,2.57-0.67c0.49-0.93,0.21-2-0.67-2.57
				c-0.34-0.22-0.67-0.45-0.99-0.69c0.13,0.1,0.25,0.2,0.38,0.29c-0.98-0.76-1.86-1.63-2.62-2.61c0.1,0.13,0.2,0.25,0.29,0.38
				c-0.48-0.62-0.9-1.26-1.27-1.95c-0.47-0.86-1.73-1.21-2.57-0.67C102.48,111.5,102.17,112.58,102.68,113.51L102.68,113.51z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M117.65,102.93c0.14,0.27,0.27,0.53,0.4,0.8c0.12,0.25,0.31,0.54,0.37,0.82c-0.11-0.25-0.12-0.28-0.04-0.09
				c0.03,0.08,0.06,0.16,0.1,0.24c0.05,0.12,0.09,0.24,0.14,0.36c0.11,0.3,0.22,0.6,0.32,0.91c0.38,1.16,0.67,2.35,0.87,3.55
				c0.07,0.44,0.5,0.91,0.86,1.12c0.4,0.24,0.99,0.33,1.44,0.19c1.05-0.34,1.48-1.27,1.31-2.31c-0.43-2.61-1.31-5.14-2.53-7.48
				c-0.46-0.87-1.74-1.2-2.57-0.67C117.44,100.94,117.16,102,117.65,102.93L117.65,102.93z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M104.68,140.41c1.47,0.59,2.94,1.18,4.42,1.77c0.42,0.17,1.07,0.03,1.44-0.19c0.39-0.23,0.76-0.67,0.86-1.12
				c0.11-0.48,0.09-1.02-0.19-1.44c-0.1-0.13-0.2-0.25-0.29-0.38c-0.23-0.23-0.51-0.39-0.83-0.48c-1.47-0.59-2.94-1.18-4.42-1.77
				c-0.42-0.17-1.07-0.03-1.44,0.19c-0.39,0.23-0.76,0.67-0.86,1.12c-0.11,0.48-0.09,1.02,0.19,1.44c0.1,0.13,0.2,0.25,0.29,0.38
				C104.09,140.16,104.37,140.32,104.68,140.41L104.68,140.41z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M115.04,124.99c1.35,2.06,2.9,4,4.65,5.74c0.7,0.69,1.97,0.75,2.65,0c0.69-0.75,0.74-1.91,0-2.65
				c-1.01-1.01-1.96-2.08-2.83-3.21c0.1,0.13,0.2,0.25,0.29,0.38c-0.53-0.7-1.04-1.41-1.52-2.15c-0.27-0.41-0.64-0.73-1.12-0.86
				c-0.45-0.12-1.05-0.07-1.44,0.19C114.9,122.95,114.47,124.11,115.04,124.99L115.04,124.99z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M128.55,118.27c0.17,0.06,0.34,0.13,0.51,0.2c-0.15-0.06-0.3-0.13-0.45-0.19c0.83,0.35,1.62,0.81,2.33,1.36
				c-0.13-0.1-0.25-0.2-0.38-0.29c0.67,0.53,1.28,1.13,1.8,1.8c-0.1-0.13-0.2-0.25-0.29-0.38c0.06,0.08,0.12,0.16,0.19,0.25
				c0.3,0.41,0.62,0.72,1.12,0.86c0.45,0.12,1.05,0.07,1.44-0.19c0.4-0.26,0.75-0.64,0.86-1.12c0.11-0.46,0.1-1.04-0.19-1.44
				c-0.39-0.54-0.8-1.06-1.27-1.53c-0.47-0.47-0.98-0.89-1.51-1.29c-0.96-0.72-2.04-1.25-3.16-1.66c-0.44-0.16-1.06-0.04-1.44,0.19
				c-0.39,0.23-0.76,0.67-0.86,1.12c-0.11,0.48-0.09,1.02,0.19,1.44C127.71,117.84,128.07,118.1,128.55,118.27L128.55,118.27z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M122.84,145.45c0.88,0.6,1.76,1.19,2.64,1.79c0.22,0.14,0.45,0.21,0.7,0.22c0.25,0.06,0.5,0.04,0.75-0.03
				c0.43-0.12,0.91-0.46,1.12-0.86c0.23-0.44,0.35-0.96,0.19-1.44c-0.06-0.15-0.13-0.3-0.19-0.45c-0.17-0.28-0.39-0.51-0.67-0.67
				c-0.88-0.6-1.76-1.19-2.64-1.79c-0.22-0.14-0.45-0.21-0.7-0.22c-0.25-0.06-0.5-0.04-0.75,0.03c-0.43,0.12-0.91,0.46-1.12,0.86
				c-0.23,0.44-0.35,0.96-0.19,1.44c0.06,0.15,0.13,0.3,0.19,0.45C122.33,145.06,122.55,145.28,122.84,145.45L122.84,145.45z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M130.78,135.54c0.55,0.49,1.05,1.03,1.5,1.61c-0.1-0.13-0.2-0.25-0.29-0.38c0.64,0.84,1.18,1.75,1.59,2.72
				c-0.06-0.15-0.13-0.3-0.19-0.45c0.01,0.02,0.02,0.05,0.03,0.07c0.06,0.24,0.17,0.46,0.34,0.64c0.13,0.2,0.31,0.37,0.53,0.48
				c0.4,0.24,0.99,0.33,1.44,0.19c0.45-0.15,0.89-0.43,1.12-0.86c0.22-0.41,0.38-0.99,0.19-1.44c-0.41-0.99-0.88-1.94-1.5-2.83
				c-0.62-0.87-1.31-1.69-2.1-2.4c-0.37-0.33-0.82-0.55-1.33-0.55c-0.46,0-1.01,0.2-1.33,0.55c-0.33,0.36-0.57,0.83-0.55,1.33
				C130.26,134.69,130.41,135.2,130.78,135.54L130.78,135.54z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M262.9,138.14c-0.36,2.64-0.73,5.28-1.09,7.92c-0.07,0.52-0.09,0.97,0.19,1.44
				c0.23,0.39,0.67,0.76,1.12,0.86c0.88,0.2,2.16-0.27,2.31-1.31c0.36-2.64,0.73-5.28,1.09-7.92c0.07-0.52,0.09-0.97-0.19-1.44
				c-0.23-0.39-0.67-0.76-1.12-0.86C264.32,136.63,263.04,137.1,262.9,138.14L262.9,138.14z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M258.55,159.81c-0.78,2.87-1.02,5.89-0.63,8.84c0.07,0.52,0.16,0.94,0.55,1.33
				c0.33,0.33,0.86,0.57,1.33,0.55c0.91-0.04,2.01-0.83,1.88-1.88c-0.18-1.37-0.23-2.75-0.14-4.12c0.02-0.34,0.05-0.67,0.09-1.01
				c0.01-0.07,0.05-0.59,0.01-0.12c0.02-0.18,0.05-0.36,0.08-0.54c0.12-0.69,0.27-1.38,0.45-2.05c0.26-0.95-0.32-2.08-1.31-2.31
				C259.87,158.27,258.82,158.8,258.55,159.81L258.55,159.81z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M287.58,144.77c-1.1,2.84-1.72,5.83-1.94,8.87c-0.07,0.98,0.91,1.92,1.88,1.88c1.08-0.05,1.8-0.82,1.88-1.88
				c0.03-0.36,0.06-0.73,0.1-1.09c0.02-0.16,0.04-0.31,0.05-0.47c0.03-0.22,0.03-0.21-0.01,0.03c0.01-0.1,0.03-0.21,0.05-0.31
				c0.1-0.67,0.23-1.34,0.38-2c0.31-1.37,0.73-2.72,1.24-4.04c0.35-0.92-0.39-2.1-1.31-2.31
				C288.81,143.21,287.96,143.78,287.58,144.77L287.58,144.77z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M309.56,151.81c-0.08,0.59-0.2,1.18-0.37,1.76c-0.08,0.29-0.17,0.57-0.27,0.86
				c-0.05,0.14-0.11,0.28-0.16,0.42c-0.08,0.18-0.06,0.14,0.06-0.14c-0.03,0.07-0.06,0.14-0.09,0.21c-0.25,0.55-0.53,1.08-0.85,1.59
				c-0.16,0.25-0.33,0.5-0.5,0.74c-0.04,0.06-0.09,0.12-0.13,0.18c0.18-0.23,0.21-0.27,0.09-0.11c-0.09,0.12-0.19,0.23-0.29,0.35
				c-0.33,0.39-0.55,0.8-0.55,1.33c0,0.46,0.2,1.01,0.55,1.33c0.7,0.64,1.97,0.8,2.65,0c1.81-2.13,3.08-4.72,3.47-7.5
				c0.07-0.52,0.09-0.97-0.19-1.44c-0.23-0.39-0.67-0.76-1.12-0.86c-0.48-0.11-1.02-0.09-1.44,0.19
				C310.05,150.93,309.63,151.34,309.56,151.81L309.56,151.81z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M282.37,168.2c-0.65,1.89-1.29,3.79-1.94,5.68c-0.32,0.93,0.36,2.09,1.31,2.31
				c1.04,0.24,1.97-0.31,2.31-1.31c0.65-1.89,1.29-3.79,1.94-5.68c0.32-0.93-0.36-2.09-1.31-2.31
				C283.64,166.66,282.71,167.21,282.37,168.2L282.37,168.2z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M265.02,186.26c-0.47,2.5-0.94,4.99-1.41,7.49c-0.18,0.96,0.27,2.07,1.31,2.31
				c0.92,0.21,2.11-0.28,2.31-1.31c0.47-2.5,0.94-4.99,1.41-7.49c0.18-0.96-0.27-2.07-1.31-2.31
				C266.41,184.74,265.22,185.23,265.02,186.26L265.02,186.26z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M285.3,191.11c-0.1,0.29-0.21,0.57-0.32,0.86c-0.03,0.07-0.06,0.14-0.09,0.21c0.19-0.48,0.08-0.19,0.03-0.08
				c-0.06,0.14-0.12,0.28-0.19,0.42c-0.27,0.57-0.56,1.13-0.88,1.67c-0.31,0.53-0.64,1.04-0.99,1.54c-0.09,0.12-0.18,0.25-0.27,0.37
				c-0.04,0.05-0.25,0.33-0.06,0.08c0.18-0.23,0.01-0.01-0.03,0.04c-0.2,0.25-0.41,0.5-0.62,0.75c-0.64,0.74-0.78,1.93,0,2.65
				c0.71,0.65,1.96,0.79,2.65,0c1.93-2.22,3.4-4.73,4.39-7.5c0.33-0.92-0.37-2.09-1.31-2.31
				C286.55,189.56,285.65,190.12,285.3,191.11L285.3,191.11z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M302.71,173.87c-1.51,2.48-3.03,4.95-4.54,7.43c-0.51,0.84-0.21,2.1,0.67,2.57
				c0.91,0.48,2.02,0.22,2.57-0.67c1.51-2.48,3.03-4.95,4.54-7.43c0.51-0.84,0.21-2.1-0.67-2.57
				C304.36,172.72,303.26,172.98,302.71,173.87L302.71,173.87z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M312.31,118.44c1.16-0.1,2.32-0.13,3.48-0.07c0.57,0.03,1.14,0.07,1.7,0.14c0.1,0.01,0.56,0.03,0.04,0
				c0.13,0.01,0.27,0.04,0.41,0.06c0.32,0.05,0.63,0.11,0.94,0.17c0.96,0.19,2.07-0.27,2.31-1.31c0.21-0.93-0.28-2.11-1.31-2.31
				c-2.5-0.48-5.04-0.66-7.57-0.43c-0.51,0.04-0.96,0.18-1.33,0.55c-0.33,0.33-0.57,0.86-0.55,1.33
				C310.47,117.51,311.26,118.53,312.31,118.44L312.31,118.44z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M330.86,103.92c2.5-0.19,5-0.38,7.5-0.57c0.98-0.07,1.92-0.81,1.88-1.88c-0.04-0.95-0.83-1.95-1.88-1.88
				c-2.5,0.19-5,0.38-7.5,0.57c-0.98,0.07-1.92,0.81-1.88,1.88C329.03,103,329.81,104,330.86,103.92L330.86,103.92z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M330.86,122.42c1.52-0.11,3.04-0.14,4.56-0.09c0.73,0.02,1.46,0.07,2.19,0.13c0.36,0.03,0.73,0.07,1.09,0.11
				c0.21,0.02,0.42,0.05,0.63,0.08c0.14,0.02,0.12,0.02-0.05-0.01c0.12,0.02,0.24,0.03,0.36,0.05c0.52,0.07,0.97,0.09,1.44-0.19
				c0.39-0.23,0.76-0.67,0.86-1.12c0.2-0.89-0.27-2.16-1.31-2.31c-3.25-0.47-6.51-0.63-9.78-0.4c-0.98,0.07-1.92,0.81-1.88,1.88
				C329.02,121.5,329.81,122.5,330.86,122.42L330.86,122.42z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M331.74,142.7c2.65,0,5.3,0,7.95,0c0.98,0,1.92-0.86,1.88-1.88c-0.05-1.02-0.82-1.88-1.88-1.88
				c-2.65,0-5.3,0-7.95,0c-0.98,0-1.92,0.86-1.88,1.88C329.91,141.84,330.68,142.7,331.74,142.7L331.74,142.7z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M351.63,100.58c2.93-0.2,5.86-0.41,8.79-0.61c0.98-0.07,1.92-0.81,1.88-1.88c-0.04-0.96-0.83-1.95-1.88-1.88
				c-2.93,0.2-5.86,0.41-8.79,0.61c-0.98,0.07-1.92,0.81-1.88,1.88C349.8,99.67,350.58,100.66,351.63,100.58L351.63,100.58z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M353.38,123.33c2.65-0.3,5.3-0.61,7.95-0.91c0.51-0.06,0.95-0.17,1.33-0.55c0.33-0.33,0.57-0.86,0.55-1.33
				c-0.04-0.92-0.83-2-1.88-1.88c-2.65,0.3-5.3,0.61-7.95,0.91c-0.51,0.06-0.95,0.17-1.33,0.55c-0.33,0.33-0.57,0.86-0.55,1.33
				C351.55,122.37,352.34,123.45,353.38,123.33L353.38,123.33z"/>
                        </g>
                    </g>
                    <g>
                        <g>
                            <path class="st0" d="M353.38,142.72c2.68,0.03,5.35-0.19,7.99-0.61c0.43-0.07,0.91-0.5,1.12-0.86c0.24-0.4,0.33-0.99,0.19-1.44
				c-0.15-0.45-0.43-0.89-0.86-1.12c-0.47-0.25-0.92-0.27-1.44-0.19c-0.15,0.02-0.29,0.05-0.44,0.07c-0.1,0.01-0.19,0.03-0.29,0.04
				c-0.18,0.03-0.19,0.03-0.04,0.01c0.28-0.04-0.21,0.03-0.26,0.03c-0.15,0.02-0.29,0.04-0.44,0.05c-0.61,0.07-1.22,0.13-1.84,0.17
				c-1.23,0.09-2.46,0.12-3.69,0.1c-0.98-0.01-1.92,0.87-1.88,1.88C351.55,141.87,352.32,142.71,353.38,142.72L353.38,142.72z"/>
                        </g>
                    </g>
                </g>
</svg>

            </a>



<%--            <a class="navbar-brand" href="#"><i class="fa-solid fa-d"></i></a>--%>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>


            <div class="collapse navbar-collapse" id="navbarNavDropdown">

                <ul class="nav navbar-nav ms-auto mb-2 mb-lg-0">
<%--                    <c:set var="user" value="${sessionScope.user}"/>--%>

                    <li class="nav-item">
                        <a class="nav-link" style="color: #e1791a;" href="#">${TEXT[COMMON_ABOUT]}</a>
                    </li>


                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" style="color: #e1791a;" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            ${TEXT[COMMON_CATEGORY]}
                        </a>
                        <ul class="dropdown-menu" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" style="color: #e1791a;" href="#">${TEXT[COMMON_PASTRTIES]}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;" href="#">${TEXT[COMMON_BAKERY]}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;" href="#"> ${TEXT[COMMON_BREAD]}</a></li>
                            <li><a class="dropdown-item" style="color: #e1791a;" href="#">${TEXT[COMMON_CHOCOLATE]}</a></li>
                        </ul>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" style="color: #e1791a;" href='<c:url value="/controller?command=go_to_signIn_page_command"/>'><i class="fas fa-lg fa-sign-in-alt"></i>&nbsp${TEXT[COMMON_SIGNIN]}</a>
                    </li>


                    <li class="nav-item dropdown" >
                        <a class="nav-link dropdown-toggle" style="color: #e1791a;" href="#" id="navbarDropdownMenuLinkLang" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                             <i class="fas fa-lg fa-language" style="color: #e1791a;"></i> ${CURRENT_LOCALIZATION_NAME}
                        </a>
                        <ul class="dropdown-menu" style="min-width: 0px;" aria-labelledby="navbarDropdownMenuLink">
                            <li><button id="ru_locale" class="dropdown-item" style="color: #e1791a;" onclick="navigation.changeLocale('${LOCALIZATION_RU}')">${LOCALIZATION_RU}</button>
<%--                                                                                                    onclick="navigation.changeLocaleEn()">--%>
                                <%--                                <a class="dropdown-item" href="#">Action</a>--%>
                            </li>
                            <li><button id="en_locale" class="dropdown-item" style="color: #e1791a;" onclick="navigation.changeLocale('${LOCALIZATION_EN}')">${LOCALIZATION_EN}</button>
                                <%--                                <a class="dropdown-item" href="#">Another action</a>--%>
                            </li>
                        </ul>
                    </li>


                </ul>
            </div>
        </div>
    </nav>
</header>

