let doorOpenTime = null;
let doorPopupShown = false;
let tempPopupShown = false;
let lastDoorState = null;

function loadData() {
    fetch("/api/history")
        .then(r => r.json())
        .then(data => {
            if (data.length === 0) return;

            data.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
            const d = data[data.length - 1];

            /* 🌡 Temperature */
            document.getElementById("tempValue").innerText =
                d.temperature + " °C";

            /* 🛡 Safety */
            const card = document.getElementById("safetyCard");
            document.getElementById("safetyText").innerText = d.safetyStatus;
            document.getElementById("safetyMsg").innerText = d.alertMessage;

            card.classList.remove("safe", "unsafe");
            card.classList.add(d.safetyStatus === "SAFE" ? "safe" : "unsafe");

            /* 🌡 Temperature popup */
            if (d.safetyStatus === "UNSAFE") {
                if (!tempPopupShown) {
                    alert("⚠️ " + d.alertMessage);
                    document.getElementById("alertSound").play();
                    tempPopupShown = true;
                }
            } else {
                tempPopupShown = false;
            }

            /* 🚪 Door */
            const doorText = document.getElementById("doorText");
            const doorAlert = document.getElementById("doorAlert");

            doorText.innerText = d.doorOpen ? "Open" : "Locked";

            // detect door state change
            if (d.doorOpen !== lastDoorState) {
                if (d.doorOpen) {
                    doorOpenTime = Date.now();   // start timer
                    doorPopupShown = false;
                } else {
                    doorOpenTime = null;
                    doorPopupShown = false;
                    doorAlert.style.display = "none";
                }
                lastDoorState = d.doorOpen;
            }

            // door open → check 1 minute
            if (d.doorOpen && doorOpenTime) {
                const openDuration = (Date.now() - doorOpenTime) / 1000;

                if (openDuration >= 60) {
                    doorAlert.style.display = "block";

                    if (!doorPopupShown) {
                        alert("⚠️ Door has been open for more than 1 minute!");
                        document.getElementById("alertSound").play();
                        doorPopupShown = true;
                    }
                }
            }

            /* ☁ Cloud */
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
