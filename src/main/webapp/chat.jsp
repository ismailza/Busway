<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chatbot</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        #chatbot-container {
            width: 400px;
            margin: 50px auto;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
        }
        #chatDisplay {
            min-height: 200px;
            max-height: 300px;
            overflow-y: auto;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        #userInput {
            width: calc(100% - 60px);
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px 0 0 5px;
            margin-bottom: 10px;
        }
        #sendButton {
            width: 60px;
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        #sendButton:hover {
            background-color: #0056b3;
        }
        .message {
            background-color: #f9f9f9;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 5px;
        }
        .bot-message {
            text-align: left;
        }
        .user-message {
            text-align: right;
        }
    </style>
</head>
<body>
    <div id="chatbot-container">
        <h1>BusWay_Bot</h1>
        <div id="chatDisplay"></div>
        <div>
            <input type="text" id="userInput" placeholder="Type your message..." autocomplete="off">
            <button id="sendButton" onclick="sendMessage()">Send</button>
        </div>
    </div>

    <script>
        function sendMessage() {
            var userInput = document.getElementById("userInput").value;
            var xhr = new XMLHttpRequest();
            xhr.open("POST", "chatbotServlet", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var response = xhr.responseText;
                    displayResponse(response);
                }
            };
            xhr.send("userInput=" + encodeURIComponent(userInput));
        }

        function displayResponse(response) {
            var chatDisplay = document.getElementById("chatDisplay");
            var messageElement = document.createElement("div");
            messageElement.textContent = "Assistant_Bot: " + response;
            messageElement.classList.add("message", "bot-message");
            chatDisplay.appendChild(messageElement);
            chatDisplay.scrollTop = chatDisplay.scrollHeight;
            document.getElementById("userInput").value = '';
        }
    </script>
</body>
</html>
