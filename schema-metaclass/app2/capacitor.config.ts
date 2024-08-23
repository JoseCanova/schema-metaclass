import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'org.nanotek.sqlite',
  appName: 'ionic7-angular-sqlite-app',
  webDir: 'www',
  loggingBehavior: 'debug',
   server: {
       androidScheme: "http"
   },
   plugins: {
	 CapacitorSQLite: {
	   androidIsEncryption: false,
	   androidBiometric: {
	     biometricAuth : false,
	     biometricTitle : "Biometric login for capacitor sqlite",
	     biometricSubTitle : "Log in using your biometric"
	   }
    }
   }
};

export default config;
