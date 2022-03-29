<html>
<head>
<title>Afisare note studenti Rezultate</title>
</head>
<body>
<h1>Afisare note studenti Rezultate</h1>
<?php
// creare variabile cu nume scurte
$nr_matr=$_POST['nr_matr'];
$nr_matr= trim($nr_matr);
$code=$_POST['code'];
$code= trim($code);
$nota=$_POST['nota'];
$nrota= trim($nota);
if (!$nr_matr || !$code || !$nota)
{
echo 'Nu ati introdus datele.';
exit;
}
if (!get_magic_quotes_gpc())
{
$nr_matr = addslashes($nr_matr);
}
// se precizează că se foloseşte PEAR DB
$user = 'student';
$pass = 'student123';
$host = 'localhost';
$db_name = 'scoala';
// conectare la BD
$connect = mysqli_connect($host, $user, $pass, $db_name);
// se verifică dacă a funcţionat conectarea
if ($connect->connect_error)
{
die ('Eroare la conectare:' . $connect->connect_error);
}
// se emite interogarea
$query = "insert into nota values(?, ?, ?)";
$stmt = mysqli_prepare($connect,$query);
$stmt->bind_param("ddd", $nr_matr,$code,$nota);
$stmt->execute();
echo $stmt->affected_rows.' Nota adaugata in BD.';
$stmt->close();

$queryy = "select * from nota where nr_matr=".$nr_matr;
$result = mysqli_query($connect, $queryy);
// verifică dacă rezultatul este în regulă
if (!$result)
{
echo 'Nu a fost gasit niciun rezultat';
exit;
}
// se obţine numărul tuplelor returnate
$num_results = mysqli_num_rows($result);
// se afişează fiecare tuplă returnată
for ($i=0; $i <$num_results; $i++)
{
$row = mysqli_fetch_assoc($result);
echo '<p><strong>'.($i+1).'.Numar matricol: ';
echo htmlspecialchars(stripslashes($row['NR_MATR']));
echo '</strong><br />Materia: ';
echo stripslashes($row['CODE']);
echo '<br />Nota: ';
echo stripslashes($row['NOTA']);
echo '</p>';
}

// deconectarea de la BD
mysqli_close($connect);
?>

</body>
</html>