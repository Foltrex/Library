<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/CSS/bookDetails.css" rel="stylesheet" type="text/css"/>
</head>
  <body>
    <h2>Books</h2>
    <table style="width:100%">
        <tr>
            <th>Title:</th>
            <td>${book.title}</td>
        </tr>
        <tr>
            <th>AuthorId:</th>
            <td>${book.authorId}</td>
        </tr>
        <tr>
            <th>Stock:</th>
            <td>${book.stock}</td>
        </tr>
        <tr>
            <th>GenreId:</th>
            <td>${book.genreId}</td>
        </tr>
      </table>
  </body>
</html>