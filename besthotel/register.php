<?php
if ($_SERVER['REQUEST_METHOD']=='POST'){

    require_once 'connect.php';
    
    extract($_POST);
    $classificacao = intval($classificacao);
    $precoDiaSemanaRegular = doubleval($precoDiaSemanaRegular);
    $precoDiaSemanaReward = doubleval($precoDiaSemanaReward);
    $precoFimSemanaRegular = doubleval($precoFimSemanaRegular);
    $precoFimSemanaReward = doubleval($precoFimSemanaReward);

    $sql = "INSERT INTO Hotel (nome, classificacao, precoDiaSemanaRegular, precoDiaSemanaReward,precoFimSemanaRegular,
                                    precoFimSemanaReward) 
                                    values ('$nome', '$classificacao', '$precoDiaSemanaRegular', 
                                    '$precoDiaSemanaReward', '$precoFimSemanaRegular', 
                                    '$precoFimSemanaReward')";
    if (mysqli_query($conn, $sql)){
        $result["sucess"]="1";
        $result["message"]="sucesso";
    }
    else{
        $result["sucess"]="0";
        $result["message"]="error";
    }

    echo json_encode($result);
    mysqli_close($conn);


}