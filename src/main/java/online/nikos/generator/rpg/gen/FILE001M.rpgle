     H NOMAIN
     FCONFFILE  IF   E           K DISK    UsrOpn ExtFile(*ExtDesc)
     F                                     ExtDesc('FILE001')
     F                                     Rename(FILE001R:CONFREC)

     D/COPY QRPGLESRC,FILE001P

     D dsRec           DS                  LikeRec(CONFREC:*INPUT)
     D dsKey_t         DS                  LikeRec(CONFREC:*KEY) Template
     D svKey           DS                  LikeRec(CONFREC:*KEY) inz

      //=======================================================================
     P OpenF_          B
      /free
            If not %Open(CONFFILE);
              Open CONFFILE;
            EndIF;
     P                 E
      //=======================================================================
     P CloseF_         B
      /free
           If %Open(CONFFILE);
             Close CONFFILE;
           EndIF;
           clear svKey;
     P                 E
      //=======================================================================
     P find_FILE001...
     P                 B                   Export
     D                 PI              N
     D  inKey                              Const LikeDS(dsKey_t)
      /free
            if inKey = svKey and svKey <> '';
              return '1'; // cached record will be used
            endif;

            openF_();
            chain inKey CONFREC dsRec;
            if not %found();
              clear dsRec;
              clear svRec;
            else;
              svKey = inKey;//cache the record
            endif;
            return %found();
     P                 E

      //=======================================================================
     P get_CHAR1_FILE001...
     P                 B                   Export
     D                 PI                  Like(dsRec.CHAR1)
      /free
            return dsRec.CHAR1;
     P                 E
      //=======================================================================
     P get_CHAR2_FILE001...
     P                 B                   Export
     D                 PI                  Like(dsRec.CHAR2)
      /free
            return dsRec.CHAR2;
     P                 E
      //=======================================================================
     P get_PACKED1_FILE001...
     P                 B                   Export
     D                 PI                  Like(dsRec.PACKED1)
      /free
            return dsRec.PACKED1;
     P                 E
      //=======================================================================
     P get_PACKED2_FILE001...
     P                 B                   Export
     D                 PI                  Like(dsRec.PACKED2)
      /free
            return dsRec.PACKED2;
     P                 E
      //=======================================================================
     P get_ZONED1_FILE001...
     P                 B                   Export
     D                 PI                  Like(dsRec.ZONED1)
      /free
            return dsRec.ZONED1;
     P                 E
      //=======================================================================
     P get_ZONED2_FILE001...
     P                 B                   Export
     D                 PI                  Like(dsRec.ZONED2)
      /free
            return dsRec.ZONED2;
     P                 E
