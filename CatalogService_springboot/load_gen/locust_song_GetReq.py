import time
import json

from locust import HttpUser, task, between

class Websiteuser(HttpUser):
    wait_time = between(1,5)
        
    @task
    def read(self):
      self.client.get("/songs/id_1")
        
