<?php

/** TODO:
 * - LOOK FOR COLLISIONS AND CHOOSE BETWEEN THE MAX COMBINATION 
 **/
$dpR1 = [];
$dpR2 = [];
// function computeMax($x, $y, $xLimit, $grid, &$dp, &$path, $xPath) {
//     $x = min($x, $xLimit);
//     if ($x<0 || $y<0) return 0;
//     if ($dp[$y][$x] ?? false) {
//         $i=$xPath-1;
//         if ($i >= 0) {
//             for ($l=$y-1; $l >= 0; $l--) {
//                 if ($path[$l][$xPath] == -1 && $path[$l][$i] != -1) {
//                     $path[$l][$xPath] = $path[$l][$i];
//                     echo "set helper path(".($l).",$i)".$path[$l][$i]."\n";
//                 }
//             }
//         }
//     	echo "dp of $y,$x found: ".$dp[$y][$x]." with path ".$path[$y-1][$xPath]."\n";
//         return $dp[$y][$x];
//     }
//     $newLimit = min($y-1, count($grid[$y])-1, $x+1);
//     if ($x > 0) {
//         $window[$x-1] = computeMax($x-1, $y-1, $newLimit, $grid, $dp, $path, $xPath);
//     }
//     if ($x <= $newLimit) {
//     	$window[$x] = computeMax($x, $y-1, $newLimit, $grid, $dp, $path, $xPath);
//     }
//     if ($x < $newLimit) {
//         $window[$x+1] = computeMax($x+1, $y-1, $newLimit, $grid, $dp, $path, $xPath);
//     }
//     echo "MYPATHH: $y, $x +++++ in y-1(".($y-1).") ".($path[$y-1][$xPath] ?? "nola")."\n";
//     if ($path[$y-1][$xPath] != -1 && array_search($path[$y-1][$xPath], $window)) {
//     	echo "$y, $x === I have path on ".($y-1).": dp[".$path[$y-1][$xPath]."] => ".$dp[$y-1][$path[$y-1][$xPath]].", with window: ".($window[$path[$y-1][$xPath]]??'NaN')." and currently on $y\n";
//     	$window[$path[$y-1][$xPath]] = $dp[$y-1][$path[$y-1][$xPath]];
//     }
//     $max = max($window);
//     $dp[$y][$x] = $grid[$y][$x] + $max;
//     $path[$y-1][$xPath] = array_keys($window, $max)[0];
//     echo "set path(".($y-1).",$xPath)".$path[$y-1][$xPath]."\n";
//     echo "Calculated dp of $y, $x: ".$dp[$y][$x]. ", xLimit: $xLimit and newlimit: $newLimit and window " . json_encode($window) . "\n";
//     return $dp[$y][$x];
// }
function computeMaxBackwards($x, $y, $xLimit, $grid, &$dp, &$path, $xPath, $tabs) {
    for($z=0;$z<$tabs;$z++) echo "    ";
    echo "currently on $y,$x and params: xLimit=$xLimit, xPath=$xPath\n";
    $x = max($x, $xLimit);
    $cols = sizeof($grid[0]);
    if ($x > $cols-1 || $y<0) return 0;
    if ($dp[$y][$x] ?? false) {
        $i=$xPath+1;
        if ($i <= $cols-1) {
            for ($l=$y-1; $l >= 0; $l--) {
                for($z=0;$z<$tabs;$z++) echo "    ";
                echo "looking for path on ($l,$xPath)=".$path[$l][$xPath]." and ($l, $i)=".$path[$l][$i]."\n";
                if ($path[$l][$xPath] == -1 && $path[$l][$i] != -1) {
                    $path[$l][$xPath] = $path[$l][$i];
                    for($z=0;$z<$tabs;$z++) echo "    ";
                    echo "set helper path(".($l).",$xPath)".$path[$l][$i]."\n";
                }
            }
        }
    	for($z=0;$z<$tabs;$z++) echo "    ";
        echo "dp of $y,$x found: ".$dp[$y][$x]." with path ".$path[$y-1][$xPath]."\n";
        return $dp[$y][$x];
    }
    $newLimit = max($cols-$y, 0, $x-1);
    for($z=0;$z<$tabs;$z++) echo "    ";
    echo "newLIM: $newLimit of $y, $x from cols=$cols max(".($cols-$y).", 0, ".($x-1).")\n";
    if ($x > $newLimit) {
        $window[$x-1] = computeMaxBackwards($x-1, $y-1, $newLimit, $grid, $dp, $path, $xPath, $tabs+1);
    }
    if ($x >= $newLimit) {
    	$window[$x] = computeMaxBackwards($x, $y-1, $newLimit, $grid, $dp, $path, $xPath, $tabs+1);
    }
    if ($x < $cols) {
        $window[$x+1] = computeMaxBackwards($x+1, $y-1, $newLimit, $grid, $dp, $path, $xPath, $tabs+1);
    }
    for($z=0;$z<$tabs;$z++) echo "    ";
    echo "MYPATHH: $y, $x +++++ in y-1(".($y-1).") ".($path[$y-1][$xPath] ?? "nola")."\n";
    if ($path[$y-1][$xPath] != -1 && array_search($path[$y-1][$xPath], $window)) {
    	echo "$y, $x === I have path on ".($y-1).": dp[".$path[$y-1][$xPath]."] => ".$dp[$y-1][$path[$y-1][$xPath]].", with window: ".($window[$path[$y-1][$xPath]]??'NaN')." and currently on $y\n";
    	$window[$path[$y-1][$xPath]] = $dp[$y-1][$path[$y-1][$xPath]];
    }
    $max = max($window);
    $dp[$y][$x] = $grid[$y][$x] + $max;
    $path[$y-1][$xPath] = array_keys($window, $max)[0];
    for($z=0;$z<$tabs;$z++) echo "    ";
    echo "set path(".($y-1).",$xPath)".$path[$y-1][$xPath]."\n";
    for($z=0;$z<$tabs;$z++) echo "    ";
    echo "Calculated dp of $y, $x: ".$dp[$y][$x]. ", xLimit: $xLimit and newlimit: $newLimit and window " . json_encode($window) . "\n";
    return $dp[$y][$x];
}
function cherryPickup($grid) {
    $cols = count($grid[0]);
    $rows = count($grid);
    // $dpR1[0][0] = $grid[0][0];
    // $dpR1[1][0] = $grid[0][0] + $grid[1][0];
    // $dpR1[1][1] = $grid[0][0] + $grid[1][1];
    $dpR2[0][$cols-1] = $grid[0][$cols-1];
    $dpR2[1][$cols-2] = $grid[0][$cols-1] + $grid[1][$cols-2];
    $dpR2[1][$cols-1] = $grid[0][$cols-1] + $grid[1][$cols-1];
    // $pathR1 = [];
    $pathR2 = [];
    // $pathR1[] = array_fill(0, $cols, 0);
    $pathR2[] = array_fill(0, $cols, 0);
    // for ($i=1; $i < $rows; $i++) {
    //     $pathR1[] = array_fill(0, $cols, -1);
    // }
    for ($i=1; $i < $rows; $i++) {
        $pathR2[] = array_fill(0, $cols, -1);
    }
    $xLimitR1 = min($cols-1, $rows-1);
    $xLimitR2 = max(0, $cols-$rows);
    for ($i=$cols-1; $i >= $xLimitR2; $i--) {
		echo "computing max on point ".($rows-1).", $i with value: ".$grid[$rows-1][$i]."\n";
        // computeMax($i, $rows-1, $xLimitR1, $grid, $dpR1, $pathR1, $i);
        computeMaxBackwards($i, $rows-1, $xLimitR2, $grid, $dpR2, $pathR2, $i,0);
        // $pathR1[$rows-1][$i] = $i;
        $pathR2[$rows-1][$i] = $i;
        echo "\n";
    }
    // $robot1Max = max($dpR1[$rows-1]);
    // $robot1PathIdx = array_keys($dpR1[$rows-1], $robot1Max)[0];
    // echo "path idx: $robot1PathIdx \n";
    $robot2Max = max($dpR2[$rows-1]);
    $robot2PathIdx = array_keys($dpR2[$rows-1], $robot2Max)[0];
    echo "path idx: $robot2PathIdx \n";
    for ($i=$rows-1; $i >= 0; $i--) { 
        // echo $pathR1[$i][$robot1PathIdx].",";
        echo $pathR2[$i][$robot2PathIdx].",";
    }
    echo "\n\n";
    
    /* for ($i=0; $i <count($dpR1); $i++) { 
	    echo "| ";
	    for ($j=0; $j < count($dpR1[$i]); $j++) {
	        echo $dpR1[$i][$j] . " | ";
	    }
	    echo "\n";
	}
    echo "path: \n";
    for ($l=0; $l < $rows; $l++) {
        for ($j=0; $j < $cols; $j++) {
            echo $pathR1[$l][$j] . ', ';
        }
        echo "\n";
    }
    echo "\ngrid: \n";
    for ($l=0; $l < $rows; $l++) {
        for ($j=0; $j < $cols; $j++) {
            echo $grid[$l][$j] . ', ';
        }
        echo "\n";
    } */
    echo "dpR2: \n";
    for ($i=0; $i <count($dpR2); $i++) { 
	    echo "| ";
	    for ($j=0; $j < $cols; $j++) {
	        echo ($dpR2[$i][$j]??0) . " | ";
	    }
	    echo "\n";
	}
    echo "path: \n";
    for ($l=0; $l < $rows; $l++) {
        for ($j=0; $j < $cols; $j++) {
            echo $pathR2[$l][$j] . ', ';
        }
        echo "\n";
    }
    echo "\ngrid: \n";
    for ($l=0; $l < $rows; $l++) {
        for ($j=0; $j < $cols; $j++) {
            echo $grid[$l][$j] . ', ';
        }
        echo "\n";
    }
}

cherryPickup([
    [1,0,0,0,0,0,1],
    [2,0,0,0,0,3,0],
    [2,0,9,0,0,0,0],
    [0,3,0,5,4,0,0],
    [1,0,2,3,0,0,6]]);