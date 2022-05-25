import numpy as np
import pywt
from numpy.fft import fftfreq
from scipy import signal
from scipy.fftpack import hilbert, fft
from scipy.io import wavfile
import matplotlib.pyplot as plt


class Signal:
    signal = None
    data = None
    sample_rate = None
    start_second = 5
    end_second = 6
    x = None
    y = None
    title_plot = None
    normalized_sig = None
    signal_enve = None
    recon_singal = None

    def get_signal(self, file_loc):
        self.signal = file_loc
        self.sample_rate, self.data = wavfile.read(self.signal)
        data0 = self.data[:, 0]
        start_time = self.start_second * self.sample_rate
        end_time = self.end_second * self.sample_rate
        time = (len(data0[start_time:end_time]) / self.sample_rate) * 1000
        t = time / (len(data0[start_time:end_time]))
        self.x = [i * t for i in range(len(data0[start_time:end_time]))]
        self.y = data0[start_time:end_time] * t

    def normalize_signal(self):
        avg_sample = np.mean(self.y)
        self.normalized_sig = (self.y - avg_sample) / (max(abs(self.y - avg_sample)))

    # def threshold_filtr(self):
    #     b, a = signal.ellip(4, 0.01, 120, 0.125)
    #     fgust = signal.filtfilt(b, a, self.normalized_sig, method="gust")
    #     plt.plot(fgust)
    #
    # def lowpass_filter(self, thresh=0.6, wavelet="db5"):
    #     thresh = thresh * np.nanmax(self.normalized_sig)
    #     coeff = pywt.wavedec(self.normalized_sig, wavelet, mode="per")
    #     coeff[1:] = (pywt.threshold(i, value=thresh, mode="soft") for i in coeff[1:])
    #     self.recon_singal = pywt.waverec(coeff, wavelet, mode="per")
    #
    # def env(self):
    #     anal_signal = hilbert(self.recon_singal)
    #     self.signal_enve = np.abs(anal_signal)
    #
    # def fft_power_freq(self, fs):
    #     signal_fft = self.recon_singal
    #     n = signal_fft.shape[0]
    #     return fftfreq(n, 1 / fs)[:signal_fft.shape[0] // 2], \
    #            np.square(np.abs(fft(signal_fft)[:n // 2] / n))

    def plot_signal(self, title_plot, choice):
        if choice == 1:
            temp_y = self.y
        elif choice == 2:
            temp_y = self.normalized_sig
        # elif choice == 3:
        #     temp_y = self.recon_singal
        # elif choice == 4:
        #     temp_y = self.signal_enve
        else:
            print("Błąd w wyborze!")
            exit()

        self.title_plot = title_plot
        plt.plot(self.x, temp_y)
        plt.xlabel("Time [ms]")
        plt.ylabel("Amplitude")
        plt.title(f"{self.title_plot} z pliku {self.signal}")
        plt.show()


o = Signal()
o.get_signal('nagranie_1.wav')
o.plot_signal("Sygnał wejściowy mono", 1)
o.normalize_signal()
o.plot_signal("Normalizacja", 2)
# o.lowpass_filter()
# o.plot_signal("lowpass", 3)
# o.env()
# o.plot_signal("enve", 4)
# o.threshold_filtr()
