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
    fftff = None
    recon_sig = None

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


    def reconstruction(self):
        temp = pywt.wavedec(self.normalized_sig, 'haar', level=4)
        for i in range(1, len(temp)):
            temp[i] = pywt.threshold(temp[i], 0.3 * max(temp[i]), mode='soft')
        self.recon_sig = pywt.waverec(temp, 'haar')


    def env(self):
        anal_signal = hilbert(self.recon_sig)
        self.signal_enve = np.abs(anal_signal)

    def fft_power_freq(self, fs):
        signal_fft = self.recon_sig
        n = signal_fft.shape[0]
        self.fftff = fftfreq(n, 1 / fs)[:signal_fft.shape[0] // 2], \
               np.square(np.abs(fft(signal_fft)[:n // 2] / n))
        return fftfreq(n, 1 / fs)[:signal_fft.shape[0] // 2], \
               np.square(np.abs(fft(signal_fft)[:n // 2] / n))

    def plot_signal(self, title_plot, choice):
        if choice == 1:
            temp_y = self.y
        elif choice == 2:
            temp_y = self.normalized_sig
        elif choice == 3:
            temp_y = self.recon_sig
        elif choice == 4:
            temp_y = self.signal_enve
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
o.reconstruction()
o.plot_signal("lowpass", 3)
o.env()
o.plot_signal("enve", 4)

def plot_power_freq(sample_rate, x_lim: None | tuple = None):
    freq, power = o.fft_power_freq(sample_rate)
    plt.plot(freq, power)
    if x_lim is not None:
        plt.xlim(x_lim)
    plt.show()

plot_power_freq(1)


