<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
<link rel="stylesheet" href="/static/css/style.css">
<style>
	span{color:grey;}
</style>
</head>
<body>
	<div class="wrap">
		<h2>Edit POST</h2>
		<form action="/board/update" method="post">
			<table class="tbl_write">
				<tbody>
					<tr>
						<td>
							<h5>No.</h5>
							<input type="text" name="id" value="${board.id }" readonly>
						</td>
					</tr>
					<tr>
						<td>
							<h5><span><small>edit &raquo; </small></span>Title.</h5>
							<input type="text" name="title" value="${board.title }" >
						</td>
					</tr>
					<tr>
						<td>
							<h5>Writer.</h5>
							<input type="text" name="writer" value="${board.writer }" readonly>
						</td>
					</tr>
					<tr>
						<td>
							<h5><span><small>edit &raquo; </small></span>Memo...</h5>
							<textarea rows="5" cols="60" name="content" >${board.content }</textarea>
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="Save" class="update_btn">
							<input type="reset" value="Cancel" class="update_btn">
						</td>
					</tr>
				</tbody>
			</table>
		</form>		
	</div>
</body>
</html>