package VoiceActivityDetection;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import VoiceActivityDetection.preprocessing.PreProcessor;
import VoiceActivityDetection.wav_file.WavFile;
import VoiceActivityDetection.wav_file.WavFileException;
import VoiceActivityDetection.feature_extraction.FrameExtractor;
import VoiceActivityDetection.feature_extraction.Utils;

public class VadTest {
	
	public double getFftEnergy(double[] samples) {
	//	FFT fft = new FFT();
	//	fft.computeFFT(samples);
	//	double[] magnitude = fft.getSpectrum();
		double[] magnitude = samples;
		double e = 0.0;
		for (int i = 0; i < magnitude.length; i++) {
			e += Math.abs(magnitude[i]);
		}
		return e;
	}
	
	@Test
	public void test() throws IOException, WavFileException {
		WavFile wav = WavFile.openWavFile(new File("test.wav"));
		int sampleRate = (int)wav.getSampleRate();
		double[] samples = wav.readWholeFile();
		//System.out.println("samples= "+samples.length);
		PreProcessor.normalizePCM(samples);
//		System.out.println("sampleRate"+ sampleRate);//16000
	
		int noiseFrames = (int)wav.getSampleRate() / 5120;
		double[][] framedSamples = FrameExtractor.extractFrames(samples, 512, 256);
		
		double averageNoiseEnergy = 0.0;
		for (int i = 0; i < framedSamples.length; i++) {
			double e = getFftEnergy(framedSamples[i]);
			
			if (i < noiseFrames) {
		//		System.out.println("Samples[" + i + "] = " + Arrays.toString(framedSamples[i]));
				averageNoiseEnergy += e;
			}
		}
		
		averageNoiseEnergy /= noiseFrames;
		double noiseStDev = 0.0;
		
		for (int i = 0; i < framedSamples.length; i++) {
			double e = getFftEnergy(framedSamples[i]);

			if (i < noiseFrames) {
			//	System.out.println("Samples[" + i + "] = " + Arrays.toString(framedSamples[i]));
				noiseStDev += (e - averageNoiseEnergy) * (e - averageNoiseEnergy);
			}
		}
		noiseStDev /= noiseFrames;
		noiseStDev = Math.sqrt(noiseStDev);
		
		System.out.println("Noise energy mean = " + averageNoiseEnergy);
		System.out.println("Noise energy standard deviation = " + noiseStDev);
		double noiseThreshold = 1.0 + averageNoiseEnergy + 2.0 * noiseStDev;
		
		int start = -1;
		int end = -1;
		
		for (int i = noiseFrames; i < framedSamples.length; i++) {
			double e = getFftEnergy(framedSamples[i]);

			if (e > noiseThreshold) {
				if (start == -1) {
					start = Utils.samplesToTime(i * 160 + 256, (int)wav.getSampleRate());
				}
			}
			else {
				if (start != -1) {
					end = Utils.samplesToTime(i * 160 + 256, (int)wav.getSampleRate());
					System.out.println("(" + start + ", " + end + ")");
					start = end = -1;
				}
			}
		}		
	}
}
