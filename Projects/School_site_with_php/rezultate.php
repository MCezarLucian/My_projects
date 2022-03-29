
<?php
// creare variabile cu nume scurte
$nr_matr=$_POST['nr_matr'];
$nr_matr= trim($nr_matr);
if (!$nr_matr)
{
echo 'Nu ati introdus criteriul de cautare. Va rog sa incercati din nou.';
exit;
}
if (!get_magic_quotes_gpc())
{
$nr_matr = addslashes($nr_matr);
}
// se precizează că se foloseşte PEAR DB
require_once('PEAR.php');
$user = 'student';
$pass = 'student123';
$host = 'localhost';
$db_name = 'scoala';
// se stabileşte şirul pentru conexiune universală sau DSN
$dsn= mysqli_connect( $host, $user, $pass, $db_name);
// se verifică dacă a funcţionat conectarea
if ($dsn->connect_error)
{
	die('Eroare la conectare:'. $dsn->connect_error);
}
// se emite interogarea
$query = "select * from vnotestud where nr_matr=".$nr_matr;
$result = mysqli_query($dsn, $query);
// verifică dacă rezultatul este în regulă
if (!$result)
{
	die('Interogare gresita :'.mysqli_error());
}
// se obţine numărul tuplelor returnate
$num_results = mysqli_num_rows($result);
// se afişează fiecare tuplă returnată
echo ' <Table style = "width:30%">
<tr>
 <th>Nr.crt.</th>
 <th>Numele</th>
 <th>Materia</th>
 <th>Felul examinarii</th>
 <th>Nota</th>
</tr>'; 
for ($i=0; $i <$num_results; $i++)
{
$row = mysqli_fetch_assoc($result);

echo '<tr><td>'.($i+1).'</td>';
echo '<td>'.htmlspecialchars(stripslashes($row['numele'])).'</td>';
echo '<td>'.stripslashes($row['titlu_mat']).'</td>';
echo '<td>'.stripslashes($row['fel_examen']).'</td>';
echo '<td>'.stripslashes($row['nota']).'</td>';

}
echo '</table>';
// deconectarea de la BD

mysqli_close($dsn);
?>
