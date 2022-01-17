"""
Main module of the server file
"""

import config

connex_app = config.connex_app

# api
connex_app.add_api("swagger.yml")

if __name__ == "__main__":
    connex_app.run(debug=True)