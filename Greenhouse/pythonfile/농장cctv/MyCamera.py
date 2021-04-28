import picamera
import time
import io
import threading

class Camera :
    thread = None
    frame = None
    start_time = 0

    # streaming 이라는 함수메소드를 쓰레드로 관리하고 화면으로 보내주는 함수
    def getStreaming(self):
        Camera.start_time = time.time()
        if Camera.thread is None:
            # 백 그라운드 쓰레드를 시작 - 쓰레드를 작업하기 위해 Thread 클래스를 생성해서 작업
            # ==> 클래스를 만들 때 Thread클래스를 상속받아 만들 수 있다.
            # streaming 메소드의 실행을 쓰레드로 처리하겠다는 의미
            Camera.thread = threading.Thread(target=self.streaming)
            Camera.thread.start() # 쓰레드를 시작하겠다는 의미
            while self.frame is None :
                time.sleep(1)

        return self.frame

    # 독립적인 실행의 한 단위로 파이카메라로 찍은 영상을 프레임단위로 지속적으로 보내주는 역할을 하는 메소드
    @classmethod
    def streaming(c):
        with picamera.PiCamera() as camera:
            camera.resolution = (480,320)
            camera.hflip = True # 회전
            camera.vflip = True

            camera.start_preview()
            time.sleep(2)

            stream = io.BytesIO()
            for f in camera.capture_continuous(stream, "jpeg", use_video_port=True):
                stream.seek(0) # 파일의 맨 처음 위치로 커서를 이동
                c.frame = stream.read()

                stream.seek(0)
                stream.truncate() # 파일의 내용을 비움