fetch("/api/history")
    .then(res => res.json())
    .then(data => {
        const body = document.getElementById("historyBody");

        data.reverse().forEach(d => {
            const dateTime = d.createdAt.split("T");
            const date = dateTime[0];
            const time = dateTime[1].split(".")[0];

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${date}</td>
                <td>${time}</td>
                <td>${d.temperature}</td>
                <td>${d.humidity}</td>
                <td>${d.doorOpen ? "Open" : "Closed"}</td>
                <td class="${d.safetyStatus === 'SAFE' ? 'safe-text' : 'unsafe-text'}">
                    ${d.safetyStatus}
                </td>
            `;

            body.appendChild(row);
        });
    });

function goBack() {
    window.location.href = "dashboard.html";
}
