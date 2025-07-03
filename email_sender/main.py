from flask import Flask, request, jsonify
import smtplib
import os
from email.mime.text import MIMEText
from dotenv import load_dotenv

app = Flask(__name__)

load_dotenv()


@app.route('/send-email', methods=['POST'])
def send_email():
    data = request.get_json()
    to_email = data.get("to")
    subject = data.get("subject")
    message = data.get("message")

    if not all([to_email, subject, message]):
        return jsonify({"error": "Missing parameters"}), 400

    try:
        smtp_server = os.environ.get("SMTP_SERVER", "smtp.yandex.ru")
        smtp_port = int(os.environ.get("SMTP_PORT", 587))
        smtp_user = os.environ["SMTP_USER"]
        smtp_pass = os.environ["SMTP_PASS"]

        msg = MIMEText(message)
        msg['Subject'] = subject
        msg['From'] = smtp_user
        msg['To'] = to_email

        with smtplib.SMTP(smtp_server, smtp_port) as server:
            server.starttls()
            server.login(smtp_user, smtp_pass)
            server.send_message(msg)

        return jsonify({"status": "ok"}), 200
    except Exception as e:
        return jsonify({"error": str(e)}), 500


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
