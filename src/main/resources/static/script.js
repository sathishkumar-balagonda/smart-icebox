function loadData() {
    fetch("/api/history")
        .then(r => r.json())
        .then(data => {
            if (data.length === 0) return;

			data.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
			const d = data[data.length - 1];


            // Temperature
            document.getElementById("tempValue").innerText =
                d.temperature + " °C";

            // Safety
            const card = document.getElementById("safetyCard");
            document.getElementById("safetyText").innerText = d.safetyStatus;
            document.getElementById("safetyMsg").innerText = d.alertMessage;

            card.style.background =
                d.safetyStatus === "SAFE" ? "#9ad7a5" : "#f28b82";

            // Door
            document.getElementById("doorText").innerText =
                d.doorOpen ? "Open" : "Locked";

            document.getElementById("doorAlert").style.display =
                d.doorOpen ? "block" : "none";

            // Cloud
            document.getElementById("cloudStatus").innerText =
                "Cloud: Connected";
        })
        .catch(() => {
            document.getElementById("cloudStatus").innerText =
                "Cloud: Disconnected";
        });
}

function goHistory() {
    window.location.href = "history.html";
}


loadData();
setInterval(loadData, 3000);
