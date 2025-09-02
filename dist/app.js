function handleImgLoad() {
    const nrImages = 20
    const randomNumber = Math.floor(Math.random() * nrImages) + 1;
    const newImgSrc = "assets/images/gif/cat" + randomNumber + ".webp"

    document.getElementById("random-cat").querySelector("img").src = newImgSrc
}


function handleCalculate() {
    let targetVolume = document.getElementById("target-volume").value;
    let ratio = document.getElementById("ratio").value;
    let alcTarget = document.getElementById("alcohol-target").value;
    let alcSource = document.getElementById("alcohol-source").value;

    // Calculate
    let herbs = Math.round(targetVolume / ratio);
    let alcohol = Math.round(targetVolume * alcTarget / alcSource);
    let water = Math.round(targetVolume - targetVolume * alcTarget / alcSource);

    // Print results
    if (targetVolume !== "") {
        document.getElementById("results-output").style.display = "block";
        document.getElementById("result-validation").style.display = "none";
        document.getElementById("herbs").textContent = "~ " + herbs + " g";
        document.getElementById("alcohol").textContent = alcohol + " ml";
        document.getElementById("water").textContent = water + " ml";
    } else {
        document.getElementById("result-validation").style.display = "block";
    }
}